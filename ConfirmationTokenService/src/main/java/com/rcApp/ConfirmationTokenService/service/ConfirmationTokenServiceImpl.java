package com.rcApp.ConfirmationTokenService.service;

import com.rcApp.ConfirmationTokenService.entitety.AppUser;
import com.rcApp.ConfirmationTokenService.entitety.ConfirmationToken;
import com.rcApp.ConfirmationTokenService.enums.*;
import com.rcApp.ConfirmationTokenService.exception.RegistrationExceptionToken;
import com.rcApp.ConfirmationTokenService.feignClients.AppUserClient;
import com.rcApp.ConfirmationTokenService.feignClients.DeviceAndLocationClient;
import com.rcApp.ConfirmationTokenService.feignClients.EmailClient;
import com.rcApp.ConfirmationTokenService.feignClients.NotificationClient;
import com.rcApp.ConfirmationTokenService.helpers.mappers.AppUserMapper;
import com.rcApp.ConfirmationTokenService.models.AppUserDTO;
import com.rcApp.ConfirmationTokenService.models.ConfirmationTokenDTO;
import com.rcApp.ConfirmationTokenService.models.EmailMessageDTO;
import com.rcApp.ConfirmationTokenService.models.RcConfirmDto;
import com.rcApp.ConfirmationTokenService.repo.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final EmailClient emailClient;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserClient appUserClient;
    private final DeviceAndLocationClient deviceAndLocationClient;
    private final DiscoveryClient discoveryClient;
    private final NotificationClient notificationClient;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${application.gateway.name}")
    private String gatewayServiceName;
    @Value("${application.gateway.confirm.path}")
    private String gatewayServiceConfirmPath;
    @Value("${application.controller.base}")
    private String base;
    @Value("${application.controller.endpoints.confirm}")
    private String confirmEndpoint;


    public String createConfirmationTokenAndSendConfirmationEmail(ConfirmationTokenDTO confirmationTokenDTO) {
        AppUser appUser = AppUserMapper.INSTANCE.dtoToAppUser(confirmationTokenDTO.getAppUserDTO());
        deleteExpiredTokenIfExist(appUser);
        LocalDateTime expires;
        switch (confirmationTokenDTO.getTokenType()) {
            case USER_CONFIRMATION:
            case DEVICE_CONFIRMATION:
                expires=LocalDateTime.now().plusMinutes(15);
                break;
            case DONOR_CONFIRM_TRANSFUSION:
                expires=LocalDateTime.now().plusDays(1);
                break;
            default:
                expires=LocalDateTime.now();
        }
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .expiresAt(expires)
                .createdAt(LocalDateTime.now())
                .tokenType(confirmationTokenDTO.getTokenType())
                .confirmationTypeId(confirmationTokenDTO.getConfirmationTypeId())
                .appUser(appUser)
                .token(token)
                .build();
        confirmationTokenRepository.saveAndFlush(confirmationToken);
        return sendConfirmationEmail(confirmationTokenDTO,token);
    }

    public String confirmToken(String token) throws RegistrationExceptionToken {
        ConfirmationToken confirmationToken = confirmationTokenRepository
                .findByToken(token)
                .orElseThrow(
                        () -> new RegistrationExceptionToken(AppMessages.TOKEN_NOT_FOUND));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new RegistrationExceptionToken(AppMessages.TOKEN_ALREADY_CONFIRMED);
        }
        LocalDateTime expireAt = confirmationToken.getExpiresAt();
        if (expireAt.isBefore(LocalDateTime.now())) {
            throw new RegistrationExceptionToken(AppMessages.TOKEN_EXPIRED);
        }
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
        switch (confirmationToken.getTokenType()) {
            case USER_CONFIRMATION:
                return appUserClient.enableUser(confirmationToken.getAppUser().getEmail());
            case DEVICE_CONFIRMATION:
                return deviceAndLocationClient.enableDevice(
                        new RcConfirmDto(confirmationToken.getConfirmationTypeId()));
            case DONOR_CONFIRM_TRANSFUSION:
                return notificationClient.confTransfusion(
                        new RcConfirmDto(confirmationToken.getConfirmationTypeId()));
            default:
                return "";
        }
    }

    @Override
    public String sendNewConfirmationToken(ConfirmationTokenDTO confirmationTokenDTO) {
           AppUserDTO appUserDTO = confirmationTokenDTO.getAppUserDTO();
           deleteExpiredTokenIfExist(AppUserMapper.INSTANCE.dtoToAppUser(appUserDTO));
           return createConfirmationTokenAndSendConfirmationEmail(confirmationTokenDTO);
    }

    private String sendConfirmationEmail(ConfirmationTokenDTO confirmationTokenDTO,String token) {
        String message = buildHtmlEmail(confirmationTokenDTO,token);
        return emailClient.sendEmail(EmailMessageDTO.builder()
                .subject(confirmationTokenDTO.getTokenType().getSubject())
                .message(message)
                .toHtml(true)
                .to(confirmationTokenDTO.getAppUserDTO().getEmail())
                .build());
    }

    private String buildHtmlEmail(ConfirmationTokenDTO confirmationTokenDTO,String token) {
//        String link = discoveryClient.getInstances(appName)
//                .stream()
//                .findAny()
//                .map(instance -> instance
//                        .getUri().toString() + base + confirmEndpoint + "?token=" + token)
//                .get();
        String link = discoveryClient.getInstances(gatewayServiceName)
                .stream()
                .findAny()
                .map(instance -> instance
                        .getUri().toString() +gatewayServiceConfirmPath+"?token=" + token)
                .get();
        String name = confirmationTokenDTO.getAppUserDTO().getFirstName();
        String bodyMsg=confirmationTokenDTO.getMessage();
        String buttonMsg;
        String expireMsg;
        switch (confirmationTokenDTO.getTokenType()) {
            case USER_CONFIRMATION:
            case DEVICE_CONFIRMATION:
                buttonMsg = "Activate Now";
                expireMsg= "15 minutes.";
                break;
            case DONOR_CONFIRM_TRANSFUSION:
                buttonMsg = "Confirm Now";
                expireMsg= "a day.";
                break;
            default:
                buttonMsg = "";
                expireMsg="";
        }
        return
                "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n"
                        +
                        "\n" +
                        "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                        "\n" +
                        "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%"+
                        ";width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                        +
                        "    <tbody><tr>\n" +
                        "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                        "        \n" +
                        "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;" +
                        "max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
                        +
                        "          <tbody><tr>\n" +
                        "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                        "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" " +
                        "border=\"0\" style=\"border-collapse:collapse\">\n"
                        +
                        "                  <tbody><tr>\n" +
                        "                    <td style=\"padding-left:10px\">\n" +
                        "                  \n" +
                        "                    </td>\n" +
                        "                    <td style=\"font-size:28px;line-height:1.315789474;" +
                        "Margin-top:4px;padding-left:10px\">\n"
                        +
                        "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;" +
                        "color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">"
                        + confirmationTokenDTO.getTokenType().getSubject() + "</span>\n"
                        +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </tbody></table>\n" +
                        "              </a>\n" +
                        "            </td>\n" +
                        "          </tr>\n" +
                        "        </tbody></table>\n" +
                        "        \n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table>\n" +
                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" " +
                        "align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"" +
                        "border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                        +
                        "    <tbody><tr>\n" +
                        "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                        "      <td>\n" +
                        "        \n" +
                        "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" " +
                        "cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                        +
                        "                  <tbody><tr>\n" +
                        "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                        "                  </tr>\n" +
                        "                </tbody></table>\n" +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" " +
                        "cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;" +
                        "max-width:580px;width:100%!important\" width=\"100%\">\n"
                        +
                        "    <tbody><tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;" +
                        "line-height:1.315789474;max-width:560px\">\n"
                        +
                        "        \n" +
                        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi "
                        + name
                        + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">"
                        + bodyMsg + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;" +
                        "padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                        "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
                        + link
                        + "\">"+buttonMsg+"</a> </p></blockquote>\n Link will expire in "+expireMsg+". <p>See you soon</p>"
                        +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                        "\n" +
                        "</div></div>";
    }

    private void deleteExpiredTokenIfExist(AppUser appUser){
        //delete old expired token if exist
        confirmationTokenRepository
                .findByAppUser(appUser)
                .filter(confirmationToken -> confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()))
                .filter(cnt-> cnt.getConfirmedAt() == null)
                .ifPresent(confirmationTokenRepository::delete);
    }
}

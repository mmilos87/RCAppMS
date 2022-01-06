package com.rcApp.notificationService.services;

import com.rcApp.notificationService.entitety.NotifiedRcDonors;
import com.rcApp.notificationService.entitety.RcUserDonor;
import com.rcApp.notificationService.entitety.TransfusionQuery;
import com.rcApp.notificationService.feignClients.ConfirmationTokenClient;
import com.rcApp.notificationService.feignClients.EmailClient;
import com.rcApp.notificationService.feignClients.RcDonorClient;
import com.rcApp.notificationService.feignClients.TransfusionQueryClient;
import com.rcApp.notificationService.helpers.classes.RcTransfusionCompatibilityHelper;
import com.rcApp.notificationService.helpers.enums.ConfirmationTokenType;
import com.rcApp.notificationService.helpers.mappers.AppUserMapper;
import com.rcApp.notificationService.helpers.mappers.RcTransfusionQueryMapper;
import com.rcApp.notificationService.models.BloodRequestDTO;
import com.rcApp.notificationService.models.ConfirmationTokenDTO;
import com.rcApp.notificationService.models.EmailMessageDTO;
import com.rcApp.notificationService.models.TransfusionQueryDTO;
import com.rcApp.notificationService.repos.NotifiedRCDonorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.rcApp.notificationService.helpers.enums.TransfusionTypes.*;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final ConfirmationTokenClient tokenClient;
    private final TransfusionQueryClient transfusionQueryClient;
    private final RcDonorClient rcDonorClient;
    private final NotifiedRCDonorsRepository notifiedRCDonorsRepository;
    private final EmailClient emailClient;

    @Override
    public String donorConfNotification(Long id) {
        NotifiedRcDonors notifiedRCDonor = notifiedRCDonorsRepository.getById(id);
        notifiedRCDonor.setConfirmDate(LocalDateTime.now());
        TransfusionQuery transfusionQuery = notifiedRCDonor.getTransfusionQuery();
        transfusionQuery.setNumberOfConfirmedDonors(transfusionQuery.getNumberOfConfirmedDonors() + 1);
        EmailMessageDTO emailMessageDTO = createEmailDtoForConfirmedTransfusion(notifiedRCDonor);
        notifiedRCDonor.getDonor().setConfirmNotification(true);
        rcDonorClient.updateDonor(notifiedRCDonor.getDonor());
        notifiedRCDonorsRepository.save(notifiedRCDonor);
        return  emailClient.sendEmail(emailMessageDTO);
    }

    @Override
    public void initialNotifications(TransfusionQueryDTO transfusionQueryDTO) {
        RcTransfusionCompatibilityHelper compatibilityHelper =
                new RcTransfusionCompatibilityHelper(transfusionQueryDTO);
        rcDonorClient
                .getListDonors(new BloodRequestDTO(
                        compatibilityHelper.getCompatibleBloodTypes(),
                        transfusionQueryDTO.getHospitalUnit().getAddress().getUserCity(),
                        LocalDateTime.now().minusDays(BLOOD.getDaysToNextGiving()),
                        LocalDateTime.now().minusDays(PLATELETS.getDaysToNextGiving()),
                        LocalDateTime.now().minusDays(BLOOD_PLASMA.getDaysToNextGiving()),
                        transfusionQueryDTO.getRecipient(), compatibilityHelper.getNumberOfDonorsToNotify()))
                .stream()
                .map(
                        donor ->
                                NotifiedRcDonors.builder()
                                        .donor(donor)
                                        .transfusionQuery(
                                                RcTransfusionQueryMapper.INSTANCE.getEntity(transfusionQueryDTO))
                                        .notifiedDate(LocalDateTime.now())
                                        .build())
                .forEach(this::sendMsgAndUpdateRepos);
    }

    @Transactional
    private void sendMsgAndUpdateRepos(NotifiedRcDonors notifiedRCDonor) {
        TransfusionQuery transfusionQuery = notifiedRCDonor.getTransfusionQuery();
        transfusionQuery.setNumberOfNotifiedDonors(transfusionQuery.getNumberOfNotifiedDonors() + 1);
        RcUserDonor donor = notifiedRCDonor.getDonor();
        String message = "In your city, an urgently needed transfusion " + transfusionQuery.getTransfusionType().name() +
                ", for the patient " + transfusionQuery.getRecipient().getFirstName()
                + " " + transfusionQuery.getRecipient().getLastName() + "," +
                " blood type " + transfusionQuery.getRecipient().getBloodType().getBloodType() + " ,\n" +
                "click on the link for if you want to donate blood and thus help this patient.";
        tokenClient.saveAndSendEmail(
                new ConfirmationTokenDTO(
                        AppUserMapper.INSTANCE.appUserToDto(donor.getAppUser()),
                        ConfirmationTokenType.DONOR_CONFIRM_TRANSFUSION,
                        notifiedRCDonor.getId(), message));
        donor.setSentNotification(true);
        rcDonorClient.updateDonor(donor);
        transfusionQueryClient.update(transfusionQuery);
        notifiedRCDonorsRepository.save(notifiedRCDonor);
    }

    private EmailMessageDTO createEmailDtoForConfirmedTransfusion(NotifiedRcDonors notifiedRcDonor){
        TransfusionQuery transfusionQuery = notifiedRcDonor.getTransfusionQuery();
        String messageTxt = "Dear " + notifiedRcDonor.getDonor().getAppUser().getFirstName() + ",\n"
                + "First of all, thanks for the transfusion confirmation.\n"
                + "Please contact " + transfusionQuery.getHospitalUnit().getHospitalUnitName()
                + " hospital, located at: \n"
                + transfusionQuery.getHospitalUnit().getAddress().getUserCity().getCityName()
                + ", township: " + transfusionQuery.getHospitalUnit().getAddress().getTownship()
                + "\nstreet: " + transfusionQuery.getHospitalUnit().getAddress().getStreet()
                + ", number: " + transfusionQuery.getHospitalUnit().getAddress().getNumber()
                + ",\n as soon as possible.\n"
                + "Look for " + transfusionQuery.getRcUserMedic().getTitle().getTitle() + " "
                + transfusionQuery.getRcUserMedic().getAppUser().getFirstName() + " "
                + transfusionQuery.getRcUserMedic().getAppUser().getLastName()
                + ", he is informed and responsible for the transfusion.\n"
                + "Transfusion type: " + transfusionQuery.getTransfusionType().name().replace("_", " ")
                + "TRANSFUSION QUERY ID: " + transfusionQuery.getId();
        String messageSubject = "Transfusion confirmed for "
                + transfusionQuery.getRecipient().getFirstName() + " " + transfusionQuery.getRecipient().getLastName();
        return EmailMessageDTO.builder()
                .to(notifiedRcDonor.getDonor().getAppUser().getEmail())
                .subject(messageSubject)
                .message(messageTxt)
                .toHtml(false)
                .build();
    }

}
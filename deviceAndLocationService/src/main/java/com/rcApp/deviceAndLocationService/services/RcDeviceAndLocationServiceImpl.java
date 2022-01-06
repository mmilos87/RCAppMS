package com.rcApp.deviceAndLocationService.services;

import com.google.common.base.Strings;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.rcApp.deviceAndLocationService.entitety.AppUser;
import com.rcApp.deviceAndLocationService.entitety.DevicesAndLocations;
import com.rcApp.deviceAndLocationService.entitety.RcDevice;
import com.rcApp.deviceAndLocationService.exception.DeviceAndLocationException;
import com.rcApp.deviceAndLocationService.feignClients.ConfirmationTokenClient;
import com.rcApp.deviceAndLocationService.helpers.enums.AppMessages;
import com.rcApp.deviceAndLocationService.helpers.enums.ConfirmationTokenType;
import com.rcApp.deviceAndLocationService.helpers.mappers.AppUserMapper;
import com.rcApp.deviceAndLocationService.models.AppUserDTO;
import com.rcApp.deviceAndLocationService.models.ConfirmationTokenDTO;
import com.rcApp.deviceAndLocationService.models.DeviceAndLocationDTO;
import com.rcApp.deviceAndLocationService.models.RcConfirmDto;
import com.rcApp.deviceAndLocationService.repo.DevicesAndLocationsRepo;
import com.rcApp.deviceAndLocationService.repo.RcDeviceRepo;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service

public class RcDeviceAndLocationServiceImpl implements RcDeviceAndLocationService {
    private final DevicesAndLocationsRepo devicesAndLocationsRepo;
    private final RcDeviceRepo rcDeviceRepo;
    private final DatabaseReader databaseReader;
    private final ConfirmationTokenClient confirmationTokenClient;
    private final Parser parser;
    private static final String UNKNOWN = "UNKNOWN";
    private AppMessages appMessage;

    public RcDeviceAndLocationServiceImpl(DevicesAndLocationsRepo devicesAndLocationsRepo,
                                          RcDeviceRepo rcDeviceRepo,
                                          DatabaseReader databaseReader, Parser parser,
                                          ConfirmationTokenClient confirmationTokenClient) {
        this.devicesAndLocationsRepo = devicesAndLocationsRepo;
        this.rcDeviceRepo = rcDeviceRepo;
        this.databaseReader = databaseReader;
        this.confirmationTokenClient = confirmationTokenClient;
        this.parser = parser;
    }
    /*
     * kada se prvi put prijavi to je to je uredjaj kome se veruje
     * svi ostali uresjaji moraju da se potvrde
     *
     */


    @Override
    public AppMessages verifyDevice(DeviceAndLocationDTO deviceAndLocationDTO) throws IOException, GeoIp2Exception, DeviceAndLocationException {

        String location = "stubal"; // error with postman
        // extractLocation(deviceAndLocationDTO.getXForwardedForIpHeader(),deviceAndLocationDTO.getRemoteAddress());
        String deviceDetails = extractDeviceDetails(deviceAndLocationDTO.getDeviceDetailsHeader());
        String deviceName = extractDeviceName(deviceAndLocationDTO.getDeviceDetailsHeader());
        AppUserDTO appUserDTO = deviceAndLocationDTO.getAppUserDTO();
        AppUser appUser = AppUserMapper.INSTANCE
                .dtoToAppUser(appUserDTO);
        Optional<DevicesAndLocations> devicesAndLocationsOpt = devicesAndLocationsRepo.findByAppUser(appUser);
        devicesAndLocationsOpt.ifPresentOrElse(
                devices -> {
                    devices.getDevices().stream()
                            .filter(name -> name.getDeviceName().equals(deviceName))
                            .filter(details -> details.getDeviceDetails().equals(deviceDetails))
                            .filter(enabled -> enabled.getEnabled().equals(true))
                            .findFirst()
                            .ifPresentOrElse(
                                    device -> { // prijavljivanje sa odobrenog uredjaja
                                        device.setLocation(location);
                                        device.setLastLogin(LocalDateTime.now());
                                        rcDeviceRepo.save(device);
                                        appMessage = AppMessages.DEVICE_IS_ENABLED;
                                    },
                                    () -> {
                                        // svako prijavljivanje sa novog uredjaja  posle prvog prijavljivanja
                                        //   dva slucaja
                                        //   prvi kad je uredja u bp ali nije odobren
                                        //   drugislucaj ako je u bazi neki drugi neodobren
                                        //treci  kad  uredjaj nije u bazi podataka
                                        var deviceAndLocation = devicesAndLocationsOpt.get();
                                        deviceAndLocation.getDevices().stream()
                                                .filter(name -> name.getDeviceName().equals(deviceName))
                                                .filter(detail -> detail.getDeviceDetails().equals(deviceDetails))
                                                .filter(dev -> !dev.getEnabled())
                                                .findFirst()
                                                .ifPresentOrElse(
                                                        device -> { // prvi
                                                            appMessage = AppMessages.DEVICE_IS_NOT_ENABLED;
                                                            String deviceName1 = device.getDeviceName();
                                                        },
                                                        () -> {
                                                            RcDevice device =
                                                                    RcDevice.builder()
                                                                            .deviceName(deviceName)
                                                                            .deviceDetails(deviceDetails)
                                                                            .location(location)
                                                                            .lastLogin(LocalDateTime.now())
                                                                            .enabled(false)
                                                                            .build();
                                                             // drugi
                                                           //ako ima vise od 1 neenablovanih obrisati koji predhodni uredjaj
                                                            //i dodati novi
//                                                            deviceAndLocation.getDevices().stream().filter(devEna -> !devEna.getEnabled())
//                                                                    .findFirst().ifPresent(nonEnabled -> {
//                                                                        deviceAndLocation.getDevices().remove(nonEnabled);
//                                                                        rcDeviceRepo.delete(nonEnabled);
//                                                                    });
                                                            //treci
                                                            deviceAndLocation.getDevices().add(device);
                                                            devicesAndLocationsRepo.save(devicesAndLocationsOpt.get());
                                                            // kreiranje tokena i slanje linka za potvrdu na email
                                                            String msg ="We noticed that you are trying to access the system from a new device. " +
                                                                    deviceDetails+"\n"+
                                                                    "Please click on the below link to activate new device: ";
                                                            ConfirmationTokenDTO tokenDTO =
                                                                    new ConfirmationTokenDTO(
                                                                            AppUserMapper.INSTANCE.appUserToDto(
                                                                                    deviceAndLocation.getAppUser()),
                                                                            ConfirmationTokenType.DEVICE_CONFIRMATION, device.getId(),msg);
                                                            confirmationTokenClient.saveAndSendEmail(tokenDTO);
                                                            appMessage = AppMessages.DEVICE_IS_NOT_IN_LIST;
                                                        });
                                    });
                },
                () -> { // slucaj  kada se prvi purt prijavljuje na sistem
                    RcDevice device =
                            RcDevice.builder()
                                    .deviceName(deviceName)
                                    .deviceDetails(deviceDetails)
                                    .location(location)
                                    .lastLogin(LocalDateTime.now())
                                    .enabled(true)
                                    .build();
                    DevicesAndLocations userDevicesAndLocations =
                            DevicesAndLocations.builder()
                                    .appUser(appUser)
                                    .devices(Collections.singletonList(device))
                                    .build();
                    devicesAndLocationsRepo.save(userDevicesAndLocations);
                    appMessage = AppMessages.DEVICE_IS_ENABLED;
                });
        return appMessage;
    }

    @Override
    public String enableDevice(RcConfirmDto rcConfirmDto) {
        int id = rcDeviceRepo.enableDeviceWithId(rcConfirmDto.getId());
        return id + " device is enabled";
    }

    private String extractDeviceName(String deviceDetailsHeader) {
        Client client = parser.parse(deviceDetailsHeader);
        if (Objects.nonNull(client)) {
            return client.device.family;
        } else {
            return UNKNOWN;
        }
    }

    private String extractDeviceDetails(String deviceDetailsHeader) {
        Client client = parser.parse(deviceDetailsHeader);
        if (Objects.nonNull(client)) {
            return new StringBuilder()
                    .append(client.os.family)
                    .append(" ")
                    .append(client.os.major)
                    .append(".")
                    .append(client.os.minor)
                    .append(" - ")
                    .append(client.userAgent.family)
                    .append(" ")
                    .append(client.userAgent.major)
                    .append(".")
                    .append(client.userAgent.minor)
                    .toString();
        } else {
            return UNKNOWN;
        }
    }

    private String extractLocation(String xForwardedForIpHeader, String remoteAddress) throws IOException, GeoIp2Exception {
        String clientIp;
        String clientXForwardedForIp = xForwardedForIpHeader;
        if (Objects.nonNull(clientXForwardedForIp)) {
            clientIp = clientXForwardedForIp.split(" *, *")[0];
        } else {
            clientIp = remoteAddress;
        }
        InetAddress ipAddress = InetAddress.getByName(clientIp);
        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (Objects.nonNull(cityResponse)
                && Objects.nonNull(cityResponse.getCity())
                && !Strings.isNullOrEmpty(cityResponse.getCity().getName())) {
            return cityResponse.getCity().getName();
        } else {
            return UNKNOWN;
        }
    }
}

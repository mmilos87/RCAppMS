package com.rcApp.notificationService.models;


import com.rcApp.notificationService.entitety.UserCity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RcAddressDTO {
    private Long id;
    private UserCity userCity;
    private String township;
    private Long postalCodeZip;
    private String street;
    private String number;


}

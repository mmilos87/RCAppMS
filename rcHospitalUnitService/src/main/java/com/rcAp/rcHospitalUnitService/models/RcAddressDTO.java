package com.rcAp.rcHospitalUnitService.models;

import com.rcAp.rcHospitalUnitService.entitety.UserCity;
import lombok.*;

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

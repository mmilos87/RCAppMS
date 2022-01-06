package com.rcAp.rcHospitalUnitService.models;

import com.rcAp.rcHospitalUnitService.entitety.RcAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalUnitDTO{

        private Long id;

        private String hospitalUnitName;

        private RcAddress address;
}

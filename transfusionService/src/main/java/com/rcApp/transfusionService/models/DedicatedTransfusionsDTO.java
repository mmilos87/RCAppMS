package com.rcApp.transfusionService.models;

import com.rcApp.transfusionService.entitety.AppUser;
import com.rcApp.transfusionService.entitety.RcTransfusion;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DedicatedTransfusionsDTO {

    private Long id;
    private RcTransfusion transfusion;
    private AppUser recipient;
}

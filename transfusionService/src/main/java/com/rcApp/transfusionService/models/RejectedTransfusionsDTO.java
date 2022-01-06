package com.rcApp.transfusionService.models;

import com.rcApp.transfusionService.entitety.RcTransfusion;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RejectedTransfusionsDTO {

    private Long id;
    private RcTransfusion transfusion;
    private String note;

}

package com.rcApp.transfusionService.entitety;

import com.rcApp.transfusionService.helpers.enums.TransfusionTypes;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class RcTransfusion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime date;
  @Enumerated(EnumType.STRING)
  private TransfusionTypes type;
  @ManyToOne
  private RcUserDonor donor;
  @ManyToOne
  private HospitalUnit hospitalUnit;
  @ManyToOne
  private RcUserMedic  rcUserMedic;
  @Builder.Default
  private Boolean isDedicated = false;
}

package com.rcApp.transfusionService.entitety;

import com.rcApp.transfusionService.helpers.enums.TransfusionTypes;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class TransfusionQuery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  private AppUser recipient;
  @ManyToOne
  private HospitalUnit hospitalUnit;
  @OneToOne
  private RcUserMedic rcUserMedic;
  @Enumerated(EnumType.STRING)
  private TransfusionTypes transfusionType;
  private Long requiredUnits;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @Builder.Default
  private Boolean onlyRecipientBloodType=false;
  @Builder.Default
  private Long numberOfNotifiedDonors=Long.valueOf(0);
  @Builder.Default
  private Long numberOfConfirmedDonors=Long.valueOf(0);
}

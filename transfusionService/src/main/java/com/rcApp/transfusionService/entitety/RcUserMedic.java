package com.rcApp.transfusionService.entitety;

import com.rcApp.transfusionService.helpers.enums.MedicTitle;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class RcUserMedic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private AppUser appUser;
  @Enumerated(EnumType.STRING)
  private MedicTitle title;
  @ManyToOne
  private HospitalUnit hospitalUnit;

}

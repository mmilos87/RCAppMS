package com.rcApp.rcTransfusionQueryService.entitety;

import com.rcApp.rcTransfusionQueryService.helpers.enums.MedicTitle;
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

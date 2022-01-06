package com.rcApp.notificationService.entitety;

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
public class NotifiedRcDonors {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private RcUserDonor donor;
  @ManyToOne
  private TransfusionQuery transfusionQuery;
  @Column(nullable = false)
  private LocalDateTime notifiedDate;
  private LocalDateTime confirmDate;

}

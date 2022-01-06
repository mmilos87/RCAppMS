package com.rcApp.ConfirmationTokenService.entitety;

import com.rcApp.ConfirmationTokenService.enums.ConfirmationTokenType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ConfirmationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String token;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @Column(nullable = false)
  private LocalDateTime expiresAt;
  private LocalDateTime confirmedAt;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ConfirmationTokenType tokenType;
  @ManyToOne
  private AppUser appUser;
  private Long confirmationTypeId;

}

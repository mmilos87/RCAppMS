package com.rcApp.rcTransfusionQueryService.entitety;

import com.rcApp.rcTransfusionQueryService.helpers.enums.AppUserRole;
import com.rcApp.rcTransfusionQueryService.helpers.enums.BloodTypes;
import com.rcApp.rcTransfusionQueryService.helpers.enums.GenderType;
import com.rcApp.rcTransfusionQueryService.helpers.enums.RCLoginAuthProvider;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AppUser {
  @Id
  private  Long id;
  private Long jmbg;
  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private GenderType gender;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  @Enumerated(EnumType.STRING)
  private BloodTypes bloodType;
  @Enumerated(EnumType.STRING)
  private AppUserRole appUserRole;
  @Builder.Default
  private Boolean locked = false;
  @Builder.Default
  private Boolean enabled = false;
  @Builder.Default
  private Boolean isBloodChecked = false;
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private RCLoginAuthProvider authProvider=RCLoginAuthProvider.LOCAL;


  public Collection<? extends GrantedAuthority> getAuthorities() {
    return appUserRole.getGrantedAuthorities();
  }

  public String getPassword() {
    return password;
  }


  public String getUsername() {
    return email;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return !locked;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }


  public boolean isEnabled() {
    return enabled;
  }
}

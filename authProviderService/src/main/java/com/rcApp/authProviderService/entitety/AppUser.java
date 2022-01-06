package com.rcApp.authProviderService.entitety;

import com.rcApp.authProviderService.helpers.enums.AppUserRole;
import com.rcApp.authProviderService.helpers.enums.BloodTypes;
import com.rcApp.authProviderService.helpers.enums.GenderType;
import com.rcApp.authProviderService.helpers.enums.RCLoginAuthProvider;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AppUser implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return appUserRole.getGrantedAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}

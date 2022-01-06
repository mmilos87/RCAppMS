package com.rcApp.deviceAndLocationService.helpers.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.rcApp.deviceAndLocationService.helpers.enums.AppUserPermission.*;


public enum AppUserRole {
  USER(Sets.newHashSet(DONOR_READ, HOSPITAL_UNIT_READ, TRANSFUSION_QUERY_READ)),
  USER_MEDIC(Sets.newHashSet(RECIPIENT_READ, RECIPIENT_WRITE, DONOR_WRITE,
      DONOR_READ, MEDIC_READ, TRANSFUSION_QUERY_READ, TRANSFUSION_QUERY_WRITE, HOSPITAL_UNIT_READ)),
  ADMIN(Sets.newHashSet(DONOR_READ, DONOR_WRITE, RECIPIENT_READ, RECIPIENT_WRITE, MEDIC_READ,
      MEDIC_WRITE, TRANSFUSION_QUERY_READ, TRANSFUSION_QUERY_WRITE, HOSPITAL_UNIT_READ,
      HOSPITAL_UNIT_WRITE)),
  ADMIN_MEDIC(Sets.newHashSet(RECIPIENT_READ, RECIPIENT_WRITE, DONOR_WRITE, DONOR_READ, MEDIC_READ,
      MEDIC_WRITE, TRANSFUSION_QUERY_READ, TRANSFUSION_QUERY_WRITE, HOSPITAL_UNIT_READ));
  private final Set<AppUserPermission> permissionSet;

  AppUserRole(Set<AppUserPermission> permissionSet) {
    this.permissionSet = permissionSet;
  }

  public Set<AppUserPermission> getPermissionSet() {
    return permissionSet;
  }

public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
  Set<SimpleGrantedAuthority> authorities = getPermissionSet().stream()
      .map(p -> new SimpleGrantedAuthority(p.getPermission()))
      .collect(Collectors.toSet());
  authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
  return authorities;
}
}

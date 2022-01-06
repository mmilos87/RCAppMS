package com.rcApp.deviceAndLocationService.repo;

import com.rcApp.deviceAndLocationService.entitety.AppUser;
import com.rcApp.deviceAndLocationService.entitety.DevicesAndLocations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DevicesAndLocationsRepo extends JpaRepository<DevicesAndLocations,Long> {
   Optional<DevicesAndLocations> findByAppUser(AppUser appUser);
}

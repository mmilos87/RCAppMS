package com.rcApp.deviceAndLocationService.repo;


import com.rcApp.deviceAndLocationService.entitety.RcDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RcDeviceRepo extends JpaRepository<RcDevice,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE RcDevice d SET d.enabled = TRUE  WHERE d.id = ?1")
    int enableDeviceWithId(Long id);
}

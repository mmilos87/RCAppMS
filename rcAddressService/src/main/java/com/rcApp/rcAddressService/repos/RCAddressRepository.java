package com.rcApp.rcAddressService.repos;

import com.rcApp.rcAddressService.entitety.RcAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RCAddressRepository extends JpaRepository<RcAddress,Long> {
}

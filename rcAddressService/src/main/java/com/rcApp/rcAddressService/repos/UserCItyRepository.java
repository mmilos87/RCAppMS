package com.rcApp.rcAddressService.repos;

import com.rcApp.rcAddressService.entitety.UserCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCItyRepository extends JpaRepository<UserCity,Long> {
}

package com.rcApp.medicService.repos;

import com.rcApp.medicService.entitety.AppUser;
import com.rcApp.medicService.entitety.RcUserMedic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RcUserMedicRepository extends JpaRepository<RcUserMedic,Long> {

  Optional<RcUserMedic> findByAppUser(AppUser appUser);
}


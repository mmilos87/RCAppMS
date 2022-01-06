package com.rcApp.loginAttemptService.repo;


import com.rcApp.loginAttemptService.entitety.AppUser;
import com.rcApp.loginAttemptService.entitety.LoginAttempts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAttemptRepo extends JpaRepository<LoginAttempts,Long> {
    Optional<LoginAttempts> findByAppUser(AppUser appUser);
}

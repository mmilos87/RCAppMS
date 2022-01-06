package com.rcApp.notificationService.repos;


import com.rcApp.notificationService.entitety.NotifiedRcDonors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifiedRCDonorsRepository extends JpaRepository<NotifiedRcDonors, Long> {
}

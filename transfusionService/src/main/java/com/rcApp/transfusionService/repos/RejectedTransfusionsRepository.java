package com.rcApp.transfusionService.repos;

import com.rcApp.transfusionService.entitety.RejectedTransfusions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectedTransfusionsRepository extends JpaRepository<RejectedTransfusions,Long> {
}

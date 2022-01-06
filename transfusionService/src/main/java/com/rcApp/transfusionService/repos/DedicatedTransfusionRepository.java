package com.rcApp.transfusionService.repos;

import com.rcApp.transfusionService.entitety.DedicatedTransfusions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DedicatedTransfusionRepository extends JpaRepository<DedicatedTransfusions,Long> {
}

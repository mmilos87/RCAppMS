package com.rcApp.transfusionService.repos;

import com.rcApp.transfusionService.entitety.RcTransfusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RcTransfusionRepository extends JpaRepository<RcTransfusion,Long> {

}

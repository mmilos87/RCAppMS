package com.rcApp.rcTransfusionQueryService.repos;

import com.rcApp.rcTransfusionQueryService.entitety.AppUser;
import com.rcApp.rcTransfusionQueryService.entitety.HospitalUnit;
import com.rcApp.rcTransfusionQueryService.entitety.TransfusionQuery;
import com.rcApp.rcTransfusionQueryService.helpers.enums.TransfusionTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransfusionQueryRepository extends JpaRepository<TransfusionQuery, Long> {

  @Query("SELECT a FROM TransfusionQuery a WHERE a.recipient = ?1 and a.transfusionType=?2")
  Optional<TransfusionQuery> findByRecipientBYType(AppUser appUser, TransfusionTypes types);

  @Query("SELECT a FROM TransfusionQuery a WHERE a.hospitalUnit = ?1 and a.transfusionType=?2")
  Optional<List<TransfusionQuery>> findAllQueryOfHUnitByType(
      HospitalUnit hospitalUnit, TransfusionTypes types);

  Optional<List<TransfusionQuery>> findByTransfusionType(TransfusionTypes type);
  @Query("SELECT a FROM TransfusionQuery a WHERE a.recipient.bloodType = ?1")

  Optional<List<TransfusionQuery>> findByHospitalUnit(HospitalUnit hospitalUnit);

    List<TransfusionQuery> findByRecipient(AppUser dtoToAppUser);
}

package com.rcAp.rcHospitalUnitService.repos;

import com.rcAp.rcHospitalUnitService.entitety.HospitalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface HospitalUnitRepository extends JpaRepository<HospitalUnit, Long> {
    Optional<HospitalUnit> findByHospitalUnitName(String hospitalUnitName);

    @Query("SELECT u.hospitalUnitName FROM HospitalUnit u")
    List<String> findAllHospitalUnitNames();
}

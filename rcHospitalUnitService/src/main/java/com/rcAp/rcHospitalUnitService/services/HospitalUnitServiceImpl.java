package com.rcAp.rcHospitalUnitService.services;

import com.rcAp.rcHospitalUnitService.entitety.HospitalUnit;
import com.rcAp.rcHospitalUnitService.entitety.RcAddress;
import com.rcAp.rcHospitalUnitService.feign.RcAddressClient;
import com.rcAp.rcHospitalUnitService.mappers.HospitalUnitMapper;
import com.rcAp.rcHospitalUnitService.mappers.RcAddressMapper;
import com.rcAp.rcHospitalUnitService.models.HospitalUnitDTO;
import com.rcAp.rcHospitalUnitService.repos.HospitalUnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class HospitalUnitServiceImpl implements HospitalUnitService {

    private final HospitalUnitRepository hospitalUnitRepository;
    private final RcAddressClient rcAddressClient;

    @Override
    public HospitalUnit saveOrUpdateHospitalUnit(HospitalUnitDTO hospitalUnitDTO) {
        HospitalUnit hospitalUnit = HospitalUnitMapper.INSTANCE.getUnit(hospitalUnitDTO);
        boolean isHuExist = hospitalUnitRepository.findByHospitalUnitName(hospitalUnitDTO.getHospitalUnitName()).isPresent();
        if (isHuExist && hospitalUnitDTO.getId()==null) throw new IllegalStateException(" Hospital unit exist");
        RcAddress rcAddress = rcAddressClient.saveOrUpdateAddress(RcAddressMapper.INSTANCE.getDTO(hospitalUnit.getAddress()));
        hospitalUnit.setAddress(rcAddress);
        return hospitalUnitRepository.save(hospitalUnit);

    }

    @Override
    public HospitalUnit getHosUnitByName(String name) {
        return hospitalUnitRepository.findByHospitalUnitName(name).get();
    }

    @Override
    public List<String> getAllHospitalUNitNames() {
        return hospitalUnitRepository.findAllHospitalUnitNames();
    }


}

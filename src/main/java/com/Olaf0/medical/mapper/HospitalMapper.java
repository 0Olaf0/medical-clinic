package com.Olaf0.medical.mapper;


import com.Olaf0.medical.model.dto.HospitalDto;
import com.Olaf0.medical.model.entity.Hospital;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalMapper {
    HospitalDto hospitalToHospitalDto(Hospital hospital);

    Hospital hospitalDtoToHospital(HospitalDto hospitalDto);


    List<HospitalDto> listHospitalToListHospitalDto(List<Hospital> hospitalList);
}

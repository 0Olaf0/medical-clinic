package com.Olaf0.medical.mapper;


import com.Olaf0.medical.model.dto.DoctorDto;
import com.Olaf0.medical.model.entity.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
    public interface DoctorMapper {
    DoctorDto doctorToDoctorDto(Doctor doctor);
    Doctor doctorDtoToDoctor(DoctorDto doctorDto);
    List<DoctorDto> listDoctorToListDoctorDto(List<Doctor> doctorList);



    }

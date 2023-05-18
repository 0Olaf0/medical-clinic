package com.Olaf0.medical.mapper;

import com.Olaf0.medical.model.dto.PatientDto;
import com.Olaf0.medical.model.entity.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto patientToPatientDto(Patient patient);

    Patient patientDtoToPatient(PatientDto patientDto);

    //liste pacjentow na liste pacjentow dto
    List<PatientDto> listPatientToListPatientDto(List<Patient> patientList);


}

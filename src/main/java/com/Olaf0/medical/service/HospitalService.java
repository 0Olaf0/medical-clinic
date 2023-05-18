package com.Olaf0.medical.service;


import com.Olaf0.medical.mapper.DoctorMapper;
import com.Olaf0.medical.mapper.HospitalMapper;

import com.Olaf0.medical.mapper.PatientMapper;
import com.Olaf0.medical.mapper.VisitMapper;
import com.Olaf0.medical.model.dto.*;


import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.model.entity.Hospital;
import com.Olaf0.medical.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class HospitalService {
    private HospitalMapper hospitalMapper;
    private HospitalRepository hospitalRepository;
    private VisitRepository visitRepository;
    private VisitMapper visitMapper;
    private DoctorRepository1 doctorRepository1;
    private DoctorMapper doctorMapper;
    private PatientRepository1 patientRepository1;
    private PatientMapper patientMapper;


    //zwrocenie wszystkich placowek
    public List<HospitalDto> getAllHospitals() {
        return hospitalMapper.listHospitalToListHospitalDto(hospitalRepository.findAll());
    }

    //dodanie placowki
    public HospitalDto addNewHospital(Hospital hospital) {
        return hospitalMapper.hospitalToHospitalDto(hospitalRepository.save(hospital));
    }

    //przypisania doktora do placowki
    public HospitalAssignDto assignDoctorToHospital(String doctorEmail, String hospitalName) {
        Doctor doctor = doctorRepository1.findByEmail(doctorEmail)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        Hospital hospital = hospitalRepository.findByName(hospitalName)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));
        //hospital.getDoctors().add(doctor);
        return HospitalAssignDto.builder()
                .hospitalId(hospital.getId())
                .doctorId(doctor.getId())
                .build();
    }

    //zwrocenie wszystkich wizyt ktore ma doktor w danej placowce
    public List<VisitDto> getVisitsByDoctorInHospital(Long doctorId, String hospitalName) {
        Hospital hospital = hospitalRepository.findByName(hospitalName)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));
        return hospital.getVisits().stream()
                .filter(visit -> visit.getDoctorId() != null)
                .filter(visit -> visit.getDoctorId().equals(doctorId))
                .map(visit -> visitMapper.visitToVisitDto(visit))
                .collect(Collectors.toList());
    }

    //zwrocenie wszystkich wizyt z danej placowki dla danego pacjenta
    public List<VisitDto> getVisitsByPatientInHospital(Long patientId, String hospitalName) {
        Hospital hospital = hospitalRepository.findByName(hospitalName)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));
        return hospital.getVisits().stream()
                .filter(visit -> visit.getPatient() != null)
                .filter(visit -> visit.getPatient().equals(patientId))
                .map(visit -> visitMapper.visitToVisitDto(visit))
                .collect(Collectors.toList());


    }

    //zwrocenie wszystkich wizyt w danej placowce
    public List<VisitDto> getAllVisitsInHospital(String hospitalName) {
        Hospital hospital = hospitalRepository.findByName(hospitalName)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));
        return hospital.getVisits().stream()
                .map(visit -> visitMapper.visitToVisitDto(visit))
                .collect(Collectors.toList());
    }
    //zwrocenie wszystkich doktorow z placowki
    public List<DoctorDto> getAllDoctorsInHospital(String hospitalName) {
        Hospital hospital = hospitalRepository.findByName(hospitalName)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));
        return hospital.getDoctors().stream()
                .map(doctor -> doctorMapper.doctorToDoctorDto(doctor))
                .collect(Collectors.toList());

    }
    public List<PatientDto> getAllPatientsFromAllHospitals() {
        return hospitalRepository.findAll().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .map(patientMapper::patientToPatientDto)
                .collect(Collectors.toList());
    }
}

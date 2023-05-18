package com.Olaf0.medical.controller;

import com.Olaf0.medical.model.dto.*;

import com.Olaf0.medical.model.entity.Hospital;
import com.Olaf0.medical.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    private HospitalService hospitalService;


    @GetMapping
    public List<HospitalDto> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @PostMapping
    public HospitalDto addNewHospital(@RequestBody Hospital hospital) {
        return hospitalService.addNewHospital(hospital);
    }


    @PostMapping("/assign")
    public HospitalAssignDto assignDoctorToHospital(@RequestBody HospitalAssignRequestDto hospitalAssignRequestDto) {
        return hospitalService.assignDoctorToHospital(hospitalAssignRequestDto.getDoctorEmail(), hospitalAssignRequestDto.getHospitalName());
    }

    @GetMapping("/visits-by-doctor")
    public List<VisitDto> getVisitsByDoctorInHospital(@RequestBody HospitalAllVisitsForDoctor hospitalAllVisitsForDoctor) {
        return hospitalService.getVisitsByDoctorInHospital(hospitalAllVisitsForDoctor.getDoctorId(), hospitalAllVisitsForDoctor.getHospitalName());
    }

    @GetMapping("/visits-by-patient")
    public List<VisitDto> getVisitsByPatientInHospital(@RequestBody HospitalAllVisitsByPatientInHospital hospitalAllVisitsByPatientInHospital) {
        return hospitalService.getVisitsByPatientInHospital(hospitalAllVisitsByPatientInHospital.getPatientId(), hospitalAllVisitsByPatientInHospital.getHospitalName());
    }

    @GetMapping("/all-visits/{hospitalName}")
    //zwrocenie wszystkich wizyt w danej placowce
    public List<VisitDto> getAllVisitsInHospital(@PathVariable String hospitalName) {
        return hospitalService.getAllVisitsInHospital(hospitalName);
    }


    //zwrocenie wszystkich doktorow z placowki
    @GetMapping("/hospital-all-doctors/{hospitalName}")
    public List<DoctorDto> getAllDoctorsInHospital(@PathVariable String hospitalName) {
        return hospitalService.getAllDoctorsInHospital(hospitalName);
    }

    //zwrocenie wszystkich pacjentow z placowek
    @GetMapping("/patients")
    public List<PatientDto> getAlLPatientsInAllHospitals() {
        return hospitalService.getAllPatientsFromAllHospitals();
    }


    /*@DeleteMapping("/doctorss")
    public void deleteDoctorFromHospital(@PathVariable Long hospitalId, Long doctorId) {
        hospitalService.deleteDoctorFromHospital(doctorId, hospitalId);
    }*/


}

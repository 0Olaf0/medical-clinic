package com.Olaf0.medical.controller;

import com.Olaf0.medical.mapper.PatientMapper;
import com.Olaf0.medical.model.dto.PatientDto;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController //Jest to adnotacja która informuje że ta klasa jest beanem i będzie obsługiwała requesty
@RequestMapping("/patients")//Jest to adnotacja która określa nam sciezke, w tym przypadku tej klasy
public class PatientController {
    private PatientService patientService;

    @GetMapping
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email) {
        return patientService.getPatientByEmail(email);
    }

    @PostMapping
    public PatientDto addNewPatient(@RequestBody Patient patient) {
        return patientService.addNewPatient(patient);
    }

    @DeleteMapping("/{email}")
    public PatientDto deletePatientByEmail(@PathVariable String email) {
        return patientService.deletePatientByEmail(email);
    }

    @PutMapping("/{email}")
    public PatientDto editPatientByEmail(@PathVariable String email, @RequestBody Patient editPatient) {
        return patientService.editPatientByEmail(email, editPatient);
    }

    @PatchMapping("/{password}")
    public PatientDto patientWithNewPassword(@PathVariable String email, @RequestBody String password) {
        return patientService.patientWithNewPassword(email, password);
    }
    @PatchMapping("/{doctorEmail}/{patientEmail}")
    public PatientDto assignDoctorToPatient(@PathVariable String doctorEmail, @PathVariable String patientEmail) {
        return patientService.assignDoctorToPatient(doctorEmail,patientEmail);
    }
}
//   @GetMapping("/{id}")
//    public PatientDto getPatientById(@PathVariable Long id,Patient patient) {
//        return patientService.getPatientById(id, patient);
//    }
//
//
//
//    @GetMapping("/byDoctorId/{id}")
//    public List<PatientDto> getAllPatientsByDoctorId(@PathVariable Long id, List patient) {
//        List<Patient> patients = patientService.getAllPatientsByDoctorId(id, patient);
//        return PatientMapper.patientToPatientDto(patients);
//
//
//    }


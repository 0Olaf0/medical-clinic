package com.Olaf0.medical.service;

import com.Olaf0.medical.mapper.PatientMapper;
import com.Olaf0.medical.mapper.VisitMapper;
import com.Olaf0.medical.model.dto.PatientDto;
import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.repository.DoctorRepository1;
import com.Olaf0.medical.repository.PatientRepository1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class PatientService {
    private PatientRepository1 patientRepository1;
    private DoctorRepository1 doctorRepository1;

    private PatientMapper patientMapper;
    private VisitMapper visitMapper;


    public List<PatientDto> getAllPatients() {

        return patientMapper.listPatientToListPatientDto(patientRepository1.findAll());

    }

    public PatientDto getPatientByEmail(String email) {
        return patientMapper.patientToPatientDto(patientRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found")));
        //return patientMapper.patientToPatientDto(patientRepository.getPatientByEmail(email)
          //      .orElseThrow(() -> new IllegalArgumentException("Patient not found")));
    }

    public PatientDto addNewPatient(Patient patient) {
        return patientMapper.patientToPatientDto(patientRepository1.save(patient));
        //return patientMapper.patientToPatientDto(patientRepository.addNewPatient(patient));
    }


    public PatientDto deletePatientByEmail(String email) {
        Patient patient = patientRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        patientRepository1.delete(patient);
        return patientMapper.patientToPatientDto(patient);

    }

    public PatientDto editPatientByEmail(String email, Patient editPatient) {
        Patient patient = patientRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        if (editPatient.getIdCardNo() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (!patient.getIdCardNo().equals(editPatient.getIdCardNo())) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editPatient.getFirstName() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editPatient.getEmail() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editPatient.getPassword() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editPatient.getBirthday() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editPatient.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editPatient.getLastName() == null) {
            throw new IllegalArgumentException("Not possible");
        }
       //return patientMapper.patientToPatientDto(patientRepository.editPatientByEmail(editPatient, patient));
        patient.update(editPatient);
        return  patientMapper.patientToPatientDto(patientRepository1.save(patient));
    }

    public PatientDto patientWithNewPassword(String email, String password) {
        Patient patient = patientRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        if (password == null) {
            throw new IllegalArgumentException("Not possible");
        }
        patient.setPassword(password);
        return patientMapper.patientToPatientDto(patientRepository1.save(patient));

        //return patientMapper.patientToPatientDto(patientRepository.patientWithNewPassword(patient,password));
    }
    public PatientDto assignDoctorToPatient(String patientEmail, String doctorEmail) {
        Patient patient = patientRepository1.findByEmail(patientEmail)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        Doctor doctor = doctorRepository1.findByEmail(doctorEmail)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        patient.getDoctors().add(doctor);
        return patientMapper.patientToPatientDto(patientRepository1.save(patient));
    }
}

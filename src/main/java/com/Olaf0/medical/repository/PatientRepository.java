package com.Olaf0.medical.repository;

import com.Olaf0.medical.model.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {
    private List<Patient> patientList = new ArrayList();

    public List<Patient> getAllPatients() {
        return patientList;
    }

    public Optional<Patient> getPatientByEmail(String email) {
        return patientList.stream()
                .filter(patient -> patient.getEmail().equals(email))
                .findFirst();
    }

    public Patient addNewPatient(Patient patient) {
        patientList.add(patient);
        return patient;
    }

    public Patient deletePatient(Patient patient) {
        patientList.remove(patient);
        return patient;
    }

    public Patient editPatientByEmail(Patient editPatient, Patient patient) {
        patient.setEmail(editPatient.getEmail());
        patient.setPhoneNumber(editPatient.getPhoneNumber());
        patient.setBirthday(editPatient.getBirthday());
        patient.setPassword(editPatient.getPassword());
        patient.setIdCardNo(editPatient.getIdCardNo());
        patient.setFirstName(editPatient.getFirstName());
        return patient;
    }

    public Patient patientWithNewPassword(Patient patient, String password) {
        patient.setPassword(password);
        return patient;
    }
}
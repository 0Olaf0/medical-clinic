package com.Olaf0.medical.service;

import com.Olaf0.medical.mapper.DoctorMapper;
import com.Olaf0.medical.model.dto.DoctorDto;
import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.repository.DoctorRepository1;
import com.Olaf0.medical.repository.PatientRepository1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class DoctorService {
    private DoctorMapper doctorMapper;
    private DoctorRepository1 doctorRepository1;
    private PatientRepository1 patientRepository1;



    public List<DoctorDto> getAllDoctors() {
        return doctorMapper.listDoctorToListDoctorDto(doctorRepository1.findAll());
    }


    public DoctorDto getDoctorByEmail(String email) {
        return doctorMapper.doctorToDoctorDto(doctorRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException()));

    }
    public DoctorDto addNewDoctor (Doctor doctor) {
        return doctorMapper.doctorToDoctorDto(doctorRepository1.save(doctor)) ;
    }
    public DoctorDto deleteDoctorByEmail (String email) {
        Doctor doctor = doctorRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException());
         doctorRepository1.delete(doctor);
         return doctorMapper.doctorToDoctorDto(doctor);
    }
    public DoctorDto editDoctorByEmail (String email,Doctor editDoctor) {
        Doctor doctor = doctorRepository1.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException());
        if (editDoctor.getName() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editDoctor.getEmail() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editDoctor.getPassword() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editDoctor.getSurname() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editDoctor.getAge() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        //doctorRepository1.(doctor,editDoctor);
        return doctorMapper.doctorToDoctorDto(doctorRepository1.save(doctor));
    }
    public DoctorDto doctorWithNewPassword(String email,String password) {
        Doctor doctor = doctorRepository1.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        if (doctor.getPassword() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        doctor.setPassword(password);
        return doctorMapper.doctorToDoctorDto(doctorRepository1.save(doctor));
    }

    public DoctorDto assignPatientToDoctor(String patientEmail, String doctorEmail) {
        Doctor doctor = doctorRepository1.findByEmail(doctorEmail)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        Patient patient = patientRepository1.findByEmail(patientEmail)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        doctor.getPatients().add(patient);
        doctorRepository1.save(doctor);
        return doctorMapper.doctorToDoctorDto(doctor);
    }
}

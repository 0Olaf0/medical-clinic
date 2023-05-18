package com.Olaf0.medical.repository;

import com.Olaf0.medical.model.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class DoctorRepository {
    private List<Doctor> doctorList = new ArrayList();



    public List<Doctor> getAllDoctors() {
        return doctorList;
    }


    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorList.stream()
                .filter(doctor -> doctor.getEmail().equals(email))
                .findFirst();

    }
    public Doctor addNewDoctor (Doctor doctor) {
        doctorList.add(doctor);
        return doctor;
    }
    public Doctor deleteDoctor (Doctor doctor) {
        doctorList.remove(doctor);
        return doctor;
    }
    public Doctor editDoctor (Doctor doctor, Doctor editDoctor) {
        doctor.setName(editDoctor.getName());
        doctor.setSurname(editDoctor.getSurname());
        doctor.setAge(editDoctor.getAge());
        doctor.setPassword(editDoctor.getPassword());
        doctor.setEmail(editDoctor.getEmail());
        return doctor;
    }
    public Doctor doctorWithNewPassword(String email,String password) {
        for (Doctor doctor : doctorList) {
            if (doctor.getEmail().equals(email)) {
                doctor.setPassword(password);
                return doctor;
            }
        }
        return null;
    }
}

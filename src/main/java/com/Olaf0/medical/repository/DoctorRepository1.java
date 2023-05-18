package com.Olaf0.medical.repository;

import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;
import java.util.Optional;

public interface DoctorRepository1 extends JpaRepository<Doctor,Long>{
    Optional<Doctor> findByEmail(String email);
    List<Doctor> findByName(String name);
    List<Doctor> findBySurname(String surname);
    List<Doctor> findByNameOrSurname(String name,String surname);
    List<Doctor> findByNameOrSurnameOrEmail(String name, String surname, String email);
    List<Doctor> findByNameAndSurname(String name, String surname);
    List<Doctor> findByNameIgnoreCase(String name);
    List<Doctor> findByHospital(Hospital hospital);
    List<Doctor> findByHospitalAndId(Hospital hospital, Long doctorId);
}

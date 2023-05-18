package com.Olaf0.medical.repository;

import com.Olaf0.medical.model.entity.Hospital;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.model.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  PatientRepository1 extends JpaRepository<Patient,Long> {
    Optional<Patient> findByEmail(String email);
    List<Patient> findByFirstName(String firstName);
    List<Patient> findByLastName(String lastName);
    List<Patient> findByFirstNameOrLastName(String firstName,String lastName);
    List<Patient> findByFirstNameOrLastNameOrEmail(String firstName, String LastName, String email);
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
    List<Patient> findByFirstNameIgnoreCase(String firstName);
    List<Patient> findByHospital(Hospital hospital);



}

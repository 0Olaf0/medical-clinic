package com.Olaf0.medical.controller;

import com.Olaf0.medical.model.dto.DoctorDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.service.DoctorService;

@AllArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private DoctorService   doctorService;

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping ("/{email}")
    public DoctorDto getDoctorByEmail (@PathVariable String email) {
        return doctorService.getDoctorByEmail(email);
    }

    @PostMapping
    public DoctorDto addNewDoctor (@RequestBody Doctor doctor) {
        return doctorService.addNewDoctor(doctor);
    }
    @DeleteMapping ("/{email}")
    public DoctorDto deleteDoctorByEmail (@PathVariable String email) {
        return doctorService.deleteDoctorByEmail(email);
    }
    @PutMapping ("/{email}")
    public DoctorDto editDoctorByEmail (@PathVariable String email, @RequestBody Doctor editDoctor) {
        return doctorService.editDoctorByEmail(email, editDoctor);
    }
    @PatchMapping ("/{email}")
    public DoctorDto doctorWithNewPassword(@PathVariable String email, @RequestBody String password) {
        return doctorService.doctorWithNewPassword(email,password );
    }

    @PatchMapping("/{doctorEmail}/{patientEmail}")
    public DoctorDto assignPatientToDoctor(@PathVariable String patientEmail, @PathVariable String doctorEmail) {
        return doctorService.assignPatientToDoctor(patientEmail,doctorEmail);
    }
}

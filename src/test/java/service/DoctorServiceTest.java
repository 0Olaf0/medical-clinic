package service;

import com.Olaf0.medical.mapper.DoctorMapper;
import com.Olaf0.medical.model.dto.DoctorDto;
import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.repository.DoctorRepository1;
import com.Olaf0.medical.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    @Mock
    private DoctorRepository1 doctorRepository1;
    @Mock
    private DoctorMapper doctorMapper;
    @InjectMocks
    private DoctorService doctorService;

    @Test
    void getAllDoctors_DoctorsExist_DoctorsReturned() {
        List<Doctor> doctors = new LinkedList<>();
        Doctor doctor = new Doctor();
        doctor.setEmail("test1");
        doctor.setPassword("asd");
        doctors.add(doctor);

        List<DoctorDto> doctorDtos = new LinkedList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail("test1");
        doctorDtos.add(doctorDto);

        when(doctorRepository1.findAll()).thenReturn(doctors);
        when(doctorMapper.listDoctorToListDoctorDto(any())).thenReturn(doctorDtos);

        List<DoctorDto> result = doctorService.getAllDoctors();

        assertEquals(1, result.size());
        assertEquals("test1", result.get(0).getEmail());
    }

    @Test
    void getDoctorByEmail_DoctorExists_DoctorDtoReturned() {
        List<Doctor> doctors = new LinkedList<>();
        Doctor doctor = new Doctor();
        doctor.setEmail("test1");
        doctor.setPassword("asd");
        doctors.add(doctor);

        List<DoctorDto> doctorDtos = new LinkedList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail("test1");
        doctorDtos.add(doctorDto);


        when(doctorRepository1.findByEmail(anyString())).thenReturn(Optional.of(doctor));
        when(doctorMapper.doctorToDoctorDto(any())).thenReturn(doctorDto);


        DoctorDto result = doctorService.getDoctorByEmail("test1");


        assertEquals("test1", result.getEmail());
    }

    @Test
    void addNewDoctor_DoctorNotExist_DoctorDtoReturned() {
        List<Doctor> doctors = new LinkedList<>();
        Doctor doctor= new Doctor();
        doctor.setEmail("test1");
        doctor.setPassword("asd");
        doctors.add(doctor);

        List<DoctorDto> doctorDtos = new LinkedList<>();
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail("test1");
        doctorDtos.add(doctorDto);


        when(doctorRepository1.save(any())).thenReturn(doctor);

        when(doctorMapper.doctorToDoctorDto(any())).thenReturn(doctorDto);


        DoctorDto result = doctorService.addNewDoctor(doctor);
        assertEquals("test1", result.getEmail());
    }

    @Test
    void deleteDoctorByEmail_DoctorExist_DoctorDtoReturned() {
        Doctor doctor = new Doctor();
        doctor.setEmail("test1");
        doctor.setPassword("asd");



        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail("test1");


        when(doctorRepository1.findByEmail(any())).thenReturn(Optional.of(doctor));
        when(doctorMapper.doctorToDoctorDto(doctor)).thenReturn(doctorDto);


        DoctorDto result = doctorService.deleteDoctorByEmail("test1");


        assertEquals(doctorDto, result);
    }

    @Test
    void editDoctorByEmail_DoctorExist_DoctorDtoReturned() {

        Doctor doctor= new Doctor();
        doctor.setEmail("test1");
        doctor.setPassword("asd");
        doctor.setName("jaca");
        doctor.setAge(23);
        doctor.setSurname("Nowak");





        Doctor editDoctor = new Doctor();
        editDoctor.setName("Kowal");
        editDoctor.setSurname("Nowak");
        editDoctor.setEmail("test2");
        editDoctor.setPassword("newAsd");
        editDoctor.setAge(30);


        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setEmail("test2");
        doctorDto.setAge(30);
        doctorDto.setName("Kowal");
        doctorDto.setSurname("Nowak");




        when(doctorRepository1.findByEmail(any())).thenReturn(Optional.of(doctor));
        when(doctorMapper.doctorToDoctorDto(any())).thenReturn(doctorDto);
        when(doctorRepository1.save(any())).thenReturn(doctor);

        DoctorDto result = doctorService.editDoctorByEmail("test2", editDoctor);


        assertEquals(editDoctor.getName(),result.getName());
        assertEquals(editDoctor.getSurname(),result.getSurname());
        assertEquals(editDoctor.getAge(),result.getAge());
        assertEquals(editDoctor.getEmail(),result.getEmail());


    }


}

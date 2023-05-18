package service;

import com.Olaf0.medical.mapper.HospitalMapper;
import com.Olaf0.medical.mapper.PatientMapper;
import com.Olaf0.medical.mapper.VisitMapper;
import com.Olaf0.medical.model.dto.HospitalAssignDto;
import com.Olaf0.medical.model.dto.HospitalDto;
import com.Olaf0.medical.model.dto.PatientDto;
import com.Olaf0.medical.model.dto.VisitDto;
import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.model.entity.Hospital;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.model.entity.Visit;
import com.Olaf0.medical.repository.DoctorRepository1;
import com.Olaf0.medical.repository.HospitalRepository;
import com.Olaf0.medical.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceTest {
    @Mock
    private HospitalRepository hospitalRepository;
    @Mock
    private HospitalMapper hospitalMapper;
    @Mock
    private DoctorRepository1 doctorRepository1;
    @Mock
    private VisitMapper visitMapper;
    @Mock
    private PatientMapper patientMapper;
    @InjectMocks
    private HospitalService hospitalService;

    @Test
    void getAllHospitals_HospitalsExist_HospitalsReturned() {
        List<Hospital> hospitals = new LinkedList<>();
        Hospital hospital = new Hospital();
        hospital.setName("koko");
        hospitals.add(hospital);

        List<HospitalDto> hospitalDtos = new LinkedList<>();
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setName("koko");
        hospitalDtos.add(hospitalDto);

        when(hospitalRepository.findAll()).thenReturn(hospitals);
        when(hospitalMapper.listHospitalToListHospitalDto(any())).thenReturn(hospitalDtos);

        List<HospitalDto> result = hospitalService.getAllHospitals();

        assertEquals(1, result.size());
        assertEquals("koko", result.get(0).getName());
    }

    @Test
    void addNewHospital_HospitalNotExists_HospitalDtoReturned() {
        List<Hospital> hospitals = new LinkedList<>();
        Hospital hospital = new Hospital();
        hospital.setName("koko");
        hospitals.add(hospital);

        List<HospitalDto> hospitalDtos = new LinkedList<>();
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setName("koko");
        hospitalDtos.add(hospitalDto);


        when(hospitalRepository.save(eq(hospital))).thenReturn((hospital));
        when(hospitalMapper.hospitalToHospitalDto(any())).thenReturn(hospitalDto);


        HospitalDto result = hospitalService.addNewHospital(hospital);


        assertEquals("koko", result.getName());
    }

    @Test
    void assignDoctorToHospital_DoctorExist_HospitalExist() {
        Doctor doctor = new Doctor();
        doctor.setEmail("janek");
        doctor.setId(1L);

        Hospital hospital = new Hospital();
        hospital.setName("krakow");
        hospital.setId(5L);


        when(hospitalRepository.findByName(any())).thenReturn(Optional.of(hospital));
        when(doctorRepository1.findByEmail(any())).thenReturn(Optional.of(doctor));

        HospitalAssignDto result = hospitalService.assignDoctorToHospital(doctor.getEmail(), hospital.getName());

        assertEquals(1L, result.getDoctorId());
        assertEquals(5L, result.getHospitalId());
    }

    @Test
    void getVisitsByDoctorInHospital_VisitsExist_DoctorExist_VisitDtoReturned() {

        Hospital hospital = new Hospital();
        hospital.setName("Krakow");

        List<Visit> visits = new LinkedList<>();
        Visit visit = new Visit();
        visit.setDoctorId(5L);
        visit.setHospital(hospital);
        visits.add(visit);
        Visit visit2 = new Visit();
        visit2.setDoctorId(1L);
        visit2.setHospital(hospital);
        visits.add(visit2);

        hospital.setVisits(visits);

        List<VisitDto> visitsDto = new LinkedList<>();
        VisitDto visitDto = new VisitDto();
        visitDto.setDoctorId(5L);
        visitDto.setHospital(hospital);
        visitsDto.add(visitDto);
        VisitDto visitDto2 = new VisitDto();
        visitDto2.setDoctorId(5L);
        visitDto2.setHospital(hospital);
        visitsDto.add(visitDto2);

        Doctor doctor = new Doctor();
        doctor.setHospital(hospital);
        doctor.setId(5L);


        when(hospitalRepository.findByName(any())).thenReturn(Optional.of(hospital));
        when(visitMapper.visitToVisitDto(any())).thenReturn(visitDto);

        List<VisitDto> result = hospitalService.getVisitsByDoctorInHospital(5L, "Krakow");
        assertEquals(5L, result.get(0).getDoctorId());
    }

    @Test
    void getAllVisitsInHospital_VisitsExist_VisitDtoReturned() {
        Hospital hospital = new Hospital();
        hospital.setName("szpital");
        hospital.setId(5L);


        List<Visit> visits = new LinkedList<>();
        Visit visit = new Visit();
        visit.setHospital(hospital);
        visits.add(visit);


        hospital.setVisits(visits);


        VisitDto visitDtos = new VisitDto();
        VisitDto visitDto = new VisitDto();
        visitDto.setHospital(hospital);


        when(hospitalRepository.findByName(any())).thenReturn(Optional.of(hospital));
        when(visitMapper.visitToVisitDto(any())).thenReturn(visitDto);

        List<VisitDto> result = hospitalService.getAllVisitsInHospital(hospital.getName());
        assertEquals(hospital.getName(), result.get(0).getHospital().getName());

    }


    @Test
    void getAllPatientsFromAllHospitals_PatientsExist_PatientDtoReturned() {

        List<Patient> patients = new LinkedList<>();
        Patient patient = new Patient();
        patient.setId(5L);
        patients.add(patient);

        List<Hospital> hospitals = new LinkedList<>();
        Hospital hospital = new Hospital();
        hospital.setName("szpital");
        hospital.setId(1L);
        hospital.setPatients(patients);
        hospitals.add(hospital);

        patient.setHospital(hospital);

        List<PatientDto> PatientsDto = new LinkedList<>();
        PatientDto patientDto = new PatientDto();
        patientDto.setId(5L);
        PatientsDto.add(patientDto);


        when(hospitalRepository.findAll()).thenReturn(hospitals);
        when(patientMapper.patientToPatientDto(any())).thenReturn(patientDto);

        List<PatientDto> result = hospitalService.getAllPatientsFromAllHospitals();

        assertEquals(hospital.getPatients().get(0).getFirstName(),result.get(0).getFirstName());








    }


}



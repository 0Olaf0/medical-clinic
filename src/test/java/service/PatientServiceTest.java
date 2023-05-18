package service;

import com.Olaf0.medical.mapper.PatientMapper;
import com.Olaf0.medical.model.dto.PatientDto;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.repository.PatientRepository1;
import com.Olaf0.medical.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    //private static final String EMAIL = PatientUtils.EMAIL;
    @Mock
    private PatientRepository1 patientRepository1;
    @Mock
    private PatientMapper patientMapper;
    @InjectMocks
    private PatientService patientService;

    //Metoda ta zwraca nam wszystkich pacjentów, liste pacjentów (PatientDto)
    //TestCase:
    //-Testowanie przypadku gdy lista jest pusta i metoda powinna zwrócić pustą listę.
    //-Testowanie przypadku gdy lista posiada pacjentow i powinna zwrocic liste pacjentow
    //3.Dane wejsciowe i mocki:
    //2-patientRepository1.findAll() -> ma zwrocic nam liste pacjentow ,
    //PatientMapper.listPatientToListPatientDto -> ma nam przemapowac liste pacjentow na liste PatientDto
    //4. Co powinny zwrocic testy:
    //Dla przypadku gdy lista zawiera  pacjentów metoda powinna zwrócić listę typu PatientDto odpowiadających pacjentom
    @Test
    void getAllPatients_PatientsExist_PatientsReturned() {
        List<Patient> patients = new LinkedList<>();
        Patient patient = new Patient();
        patient.setEmail("test1");
        patient.setPassword("asd");
        patient.setPhoneNumber("555");
        patients.add(patient);

        List<PatientDto> patientDtos = new LinkedList<>();
        PatientDto patientDto = new PatientDto();
        patientDto.setEmail("test1");
        patientDtos.add(patientDto);

        // kiedy zostanie wywolana metoda patientRepository.findAll() to zwroc patients (liste pacjentow)
        when(patientRepository1.findAll()).thenReturn(patients);
        // kiedy zostanei wywolany model mapper z obojetnie jakimi paramterami (any()) to zwroc
        when(patientMapper.listPatientToListPatientDto(any())).thenReturn(patientDtos);

        List<PatientDto> result = patientService.getAllPatients();

        assertEquals(1, result.size());
        assertEquals("test1", result.get(0).getEmail());
    }

    @Test
    void getPatientByEmail_PatientExists_PatientDtoReturned() {
        List<Patient> patients = new LinkedList<>();
        Patient patient = new Patient();
        patient.setEmail("test1");
        patient.setPassword("asd");
        patient.setPhoneNumber("555");
        patients.add(patient);

        List<PatientDto> patientDtos = new LinkedList<>();
        PatientDto patientDto = new PatientDto();
        patientDto.setEmail("test1");
        patientDtos.add(patientDto);

        // kiedy zostanie wywolana patientRepository1.findByEmail to zwroc Optional<Patient>
        when(patientRepository1.findByEmail(anyString())).thenReturn(Optional.of(patient));
        //kiedy zostanie wywolany mapper z obojetnymi parametrami any to zrwoc patientDto
        when(patientMapper.patientToPatientDto(any())).thenReturn(patientDto);

        // olafo robi test
        PatientDto result = patientService.getPatientByEmail("test1");

        // olafo sprawdza wynik
        assertEquals("test1", result.getEmail());
    }

    @Test
    void addNewPatient_PatientNotExist_PatientDtoReturned() {
        List<Patient> patients = new LinkedList<>();
        Patient patient = new Patient();
        patient.setEmail("test1");
        patient.setPassword("asd");
        patient.setPhoneNumber("555");
        patients.add(patient);

        List<PatientDto> patientDtos = new LinkedList<>();
        PatientDto patientDto = new PatientDto();
        patientDto.setEmail("test1");
        patientDtos.add(patientDto);

        //kiedy
        when(patientRepository1.save(any())).thenReturn(patient);
        //
        when(patientMapper.patientToPatientDto(any())).thenReturn(patientDto);

        //test
        PatientDto result = patientService.addNewPatient(patient);
        //wynik
        assertEquals("test1", result.getEmail());
    }

    @Test
    void deletePatientByEmail_PatientExist_PatientDtoReturned() {
        Patient patient = new Patient();
        patient.setEmail("test1");
        patient.setPassword("asd");
        patient.setPhoneNumber("555");


        PatientDto patientDto = new PatientDto();
        patientDto.setEmail("test1");


        when(patientRepository1.findByEmail(any())).thenReturn(Optional.of(patient));
        when(patientMapper.patientToPatientDto(patient)).thenReturn(patientDto);


        PatientDto result = patientService.deletePatientByEmail("test1");


        assertEquals(patientDto, result);
    }

    @Test
    void editPatientByEmail_PatientExist_PatientDtoReturned() {

        Patient patient = new Patient();
        patient.setEmail("test1");
        patient.setPassword("asd");
        patient.setPhoneNumber("555");
        patient.setIdCardNo("123456789");


        Patient editPatient = new Patient();
        editPatient.setIdCardNo("123456789");
        editPatient.setFirstName("Olo");
        editPatient.setLastName("Molo");
        editPatient.setEmail("test1");
        editPatient.setPassword("newAsd");
        editPatient.setBirthday(LocalDate.of(2000, 1, 1));
        editPatient.setPhoneNumber("777");

        PatientDto patientDto = new PatientDto();
        patientDto.setEmail("test1");
        patientDto.setPhoneNumber("777");
        patientDto.setBirthday(LocalDate.of(2000,1,1));
        patientDto.setFirstName("Olo");
        patientDto.setLastName("Molo");



        when(patientRepository1.findByEmail(any())).thenReturn(Optional.of(patient));
        when(patientMapper.patientToPatientDto(any())).thenReturn(patientDto);
        when(patientRepository1.save(any())).thenReturn(patient);

        PatientDto result = patientService.editPatientByEmail("test1", editPatient);


        assertEquals(editPatient.getFirstName(),result.getFirstName());
        assertEquals(editPatient.getLastName(),result.getLastName());
        assertEquals(editPatient.getBirthday(),result.getBirthday());
        assertEquals(editPatient.getPhoneNumber(),result.getPhoneNumber());
        assertEquals(editPatient.getEmail(),result.getEmail());


    }

    @Test
    void patientWithNewPassword_patientExist_patientDtoReturned() {

        Patient patient = new Patient();
        patient.setEmail("test1");
        patient.setPassword("asd");
        patient.setPhoneNumber("555");

        String newPassword = "ASD";

        PatientDto patientDto = new PatientDto();
        patientDto.setEmail("test1");
        patientDto.setPhoneNumber("555");
        patient.setPassword("asd");


        when(patientRepository1.findByEmail(any())).thenReturn(Optional.of(patient));
        when(patientMapper.patientToPatientDto(eq(patient))).thenReturn(patientDto);
        when(patientRepository1.save(eq(patient))).thenReturn(patient);

        PatientDto result = patientService.patientWithNewPassword("test1", "ASD");

        assertEquals(patient.getEmail(),result.getEmail());
        assertEquals(patient.getPhoneNumber(),result.getPhoneNumber());

    }

}


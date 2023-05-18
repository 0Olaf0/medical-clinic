package service;


import com.Olaf0.medical.mapper.VisitMapper;
import com.Olaf0.medical.model.dto.VisitCreatedDto;
import com.Olaf0.medical.model.dto.VisitDto;
import com.Olaf0.medical.model.entity.Hospital;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.model.entity.Visit;
import com.Olaf0.medical.repository.VisitRepository;
import com.Olaf0.medical.service.VisitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class VisitServiceTest {
    @Mock
    private VisitRepository visitRepository;
    @Mock
    private VisitMapper visitMapper;
    @InjectMocks
    private VisitService visitService;

    @Test
    void getAllVisits_VisitsExist_ReturnVisitDto() {
        List<Visit> visits = new LinkedList<>();
        Visit visit = new Visit();
        visit.setId(5L);
        visits.add(visit);

        List<VisitDto> visitDtos = new LinkedList<>();
        VisitDto visitDto = new VisitDto();
        visitDto.setId(5L);
        visitDtos.add(visitDto);

        when(visitRepository.findAll()).thenReturn(visits);
        when(visitMapper.listVisitToListVisitDto(any())).thenReturn(visitDtos);

        List<VisitDto> result = visitService.getAllVisits();

        assertEquals(1,result.size());
        assertEquals(5L,result.get(0).getId());

    }
    @Test
    void getVisitById_VisitExists_VisitDtoReturned() {
    //pytanie czy jest tutaj sens robic liste? w pacjencie mielismy liste wiec
        // przerobilem tak jak tam lecz wstepnie zrobilem samego pacjenta bez dodawania do listy
        List<Visit> visits = new LinkedList<>();
        Visit visit = new Visit();
        visit.setId(5L);
        visits.add(visit);

        List<VisitDto> visitDtos = new LinkedList<>();
        VisitDto visitDto = new VisitDto();
        visitDto.setId(5L);
        visitDtos.add(visitDto);

        when(visitRepository.findById(any())).thenReturn(Optional.of(visit));
        when(visitMapper.visitToVisitDto(any())).thenReturn(visitDto);

        VisitDto result = visitService.getVisitById(5L);

        assertEquals(5L,result.getId());

    }

    @Test
    void addNewVisit_VisitNotExist_VisitDtoReturned() {
        List<Visit> visits = new LinkedList<>();
        Visit visit = new Visit();
        visit.setId(5L);
        visits.add(visit);

        List<VisitDto> visitDtos = new LinkedList<>();
        VisitDto visitDto = new VisitDto();
        visitDto.setId(5L);
        visitDtos.add(visitDto);

        when(visitRepository.save(any())).thenReturn(visit);
        when(visitMapper.visitToVisitDto(any())).thenReturn(visitDto);

        VisitDto result = visitService.addNewVisit(visit);

        assertEquals(5L,result.getId());
    }

    @Test
    void deleteVisitById_VisitExist_VisitDtoReturned() {
        Visit visit = new Visit();
        visit.setId(5L);


        VisitDto visitDto = new VisitDto();
        visitDto.setId(5L);

        when(visitRepository.findById(any())).thenReturn(Optional.of(visit));
        when(visitMapper.visitToVisitDto(visit)).thenReturn(visitDto);

        VisitDto result = visitService.deleteVisitById(5L);

        assertEquals(5L,result.getId());
    }

    @Test
    void editVisitById_VisitExist_VisitDtoReturned() {
        Visit visit = new Visit();
        visit.setId(5L);
        visit.setReasonOfVisit("kaszel");
        visit.setDiagnosis("jest nie dobrze");
        visit.setTreatment("syrop");
        visit.setDoctorId(2L);
        visit.setPatient(new Patient());
        visit.setHospital(new Hospital());
        visit.setTerm(LocalDateTime.of(2010,10,12,19,05));


        Visit editVisit = new Visit();
        editVisit.setId(7L);
        editVisit.setReasonOfVisit("bol gardla");
        editVisit.setDiagnosis("jest ok");
        editVisit.setTreatment("tabletki");
        editVisit.setDoctorId(5L);
        editVisit.setPatient(new Patient());
        editVisit.setHospital(new Hospital());
        editVisit.setTerm(LocalDateTime.of(2022,12,23,22,12));

        VisitDto visitDto = new VisitDto();
        visitDto.setId(7L);
        visitDto.setReasonOfVisit("bol gardla");
        visitDto.setDiagnosis("jest ok");
        visitDto.setTreatment("tabletki");
        visitDto.setDoctorId(5L);
        editVisit.setTerm(LocalDateTime.of(2022,12,23,22,12));


        when(visitRepository.findById(any())).thenReturn(Optional.of(visit));
        when(visitMapper.visitToVisitDto(any())).thenReturn(visitDto);
        when(visitRepository.save(any())).thenReturn(visit);

        VisitDto result = visitService.editVisitById(7L,editVisit);

        assertEquals(editVisit.getId(),result.getId());
        assertEquals(editVisit.getReasonOfVisit(),result.getReasonOfVisit());
    }

    @Test
    void createVisit_VisitNotExist_VisitDtoReturned() {
        Visit visit = new Visit();
        visit.setDoctorId(5L);
        visit.setTerm(LocalDateTime.of(2022,10,8,10,00));


        VisitCreatedDto visitCreatedDto = new VisitCreatedDto();
        visitCreatedDto.setDoctorId(6L);
        visitCreatedDto.setTerm(LocalDateTime.of(2023,12,10,12,00));


        VisitDto visitDto = new VisitDto();
        visitDto.setDoctorId(6L);
        visitDto.setTerm(LocalDateTime.of(2023,12,10,12,00));

        when(visitRepository.save(any())).thenReturn(visit);
        when(visitMapper.visitCreatedDtoToVisit(any())).thenReturn(visit);
        when(visitMapper.visitToVisitDto(any())).thenReturn(visitDto);

        VisitDto result = visitService.createVisit(visitCreatedDto);

        assertEquals(6L,result.getDoctorId());
    }



}

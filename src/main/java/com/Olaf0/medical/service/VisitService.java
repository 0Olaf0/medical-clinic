package com.Olaf0.medical.service;


import com.Olaf0.medical.mapper.PatientMapper;
import com.Olaf0.medical.mapper.VisitMapper;
import com.Olaf0.medical.model.dto.VisitCreatedDto;
import com.Olaf0.medical.model.dto.VisitDto;
import com.Olaf0.medical.model.entity.Visit;
import com.Olaf0.medical.repository.PatientRepository1;
import com.Olaf0.medical.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class VisitService {
    private VisitRepository visitRepository;
    private VisitMapper visitMapper;
    private PatientRepository1 patientRepository1;
    private PatientMapper patientMapper;
    private Visit visit;


    public List<VisitDto> getAllVisits() {
        return visitMapper.listVisitToListVisitDto(visitRepository.findAll());

    }

    public VisitDto getVisitById(Long id) {
         visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
         return visitMapper.visitToVisitDto(visit);
    }

    public VisitDto addNewVisit(Visit visit) {
        return visitMapper.visitToVisitDto(visitRepository.save(visit));
    }


    public VisitDto deleteVisitById(Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
         visitRepository.delete(visit);
         return visitMapper.visitToVisitDto(visit);
    }

    public VisitDto editVisitById(Long id, Visit editVisit) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found"));
        if (editVisit.getId() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (visit.getId().equals(editVisit.getId())) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editVisit.getPatient() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editVisit.getDoctorId() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editVisit.getTerm() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editVisit.getReasonOfVisit() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editVisit.getDiagnosis() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        if (editVisit.getTreatment() == null) {
            throw new IllegalArgumentException("Not possible");
        }
        visit.update(editVisit);
        return visitMapper.visitToVisitDto(visitRepository.save(visit));
    }
    public VisitDto createVisit(VisitCreatedDto visitCreatedDto) {
        Visit visit = visitRepository.save(visitMapper.visitCreatedDtoToVisit(visitCreatedDto));
        return visitMapper.visitToVisitDto(visit);

    }



    // public VisitDto assignPatientToVisit(String patientEmail,Long visitId) {
     //   Patient patient = patientRepository1.findByEmail(patientEmail)
           //     .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
     //   Visit visit = visitMapper.visitToVisitDto(visitRepository.findById(visitId)
        //        .orElseThrow(() -> new IllegalArgumentException("Visit not found"));


      //  patient.getVisits().add(visit);
       // patientRepository1.save(patient);
      //  return visitMapper.visitToVisitDto(visit);
   // }
}

package com.Olaf0.medical.controller;

import com.Olaf0.medical.model.dto.VisitCreatedDto;
import com.Olaf0.medical.model.dto.VisitDto;
import com.Olaf0.medical.model.entity.Visit;
import com.Olaf0.medical.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/visits")
public class VisitController {
    private VisitService visitService;

    @GetMapping
    public List<VisitDto> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/{id}")
    public VisitDto getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @PostMapping("/{visits}/{visit}")
    public VisitDto addNewVisit(@RequestBody Visit visit) {
        return visitService.addNewVisit(visit);
    }

    @DeleteMapping("/{id}")
    public VisitDto deleteVisitById(@PathVariable Long id) {
        return visitService.deleteVisitById(id);
    }

    @PutMapping("/{id}")
    public VisitDto editVisitById(@PathVariable Long id, @RequestBody Visit editVisit) {
        return visitService.editVisitById(id, editVisit);
    }
    @PostMapping("/{visits}/{visitCreatedDto}")
    public VisitDto createVisit(@RequestBody VisitCreatedDto visitCreatedDto) {
        return visitService.createVisit(visitCreatedDto);
    }
    //@PatchMapping("/{visitId}/{patientEmail}")
   // public VisitDto assignPatientToVisit(@PathVariable String patientEmail, @PathVariable Long visitId){
   //return visitService.assignPatientToVisit(patientEmail,visitId);
   // }
}

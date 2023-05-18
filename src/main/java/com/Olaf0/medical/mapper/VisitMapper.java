package com.Olaf0.medical.mapper;

import com.Olaf0.medical.model.dto.PatientDto;
import com.Olaf0.medical.model.dto.VisitCreatedDto;
import com.Olaf0.medical.model.dto.VisitDto;
import com.Olaf0.medical.model.entity.Patient;
import com.Olaf0.medical.model.entity.Visit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitDto visitToVisitDto(Visit visit);

    Visit visitDtoToVisit(VisitDto visitDto);


    List<VisitDto> listVisitToListVisitDto(List<Visit> visitList);
    Visit visitCreatedDtoToVisit(VisitCreatedDto visitCreatedDto);

}

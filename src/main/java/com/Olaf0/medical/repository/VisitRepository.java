package com.Olaf0.medical.repository;



import com.Olaf0.medical.model.dto.VisitDto;
import com.Olaf0.medical.model.entity.Hospital;
import com.Olaf0.medical.model.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit,Long> {
    Optional<Visit> findById(Long visitId);
    List<VisitDto> findByHospital(Hospital hospital);

}

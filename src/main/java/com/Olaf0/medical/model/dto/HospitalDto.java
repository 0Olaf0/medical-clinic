package com.Olaf0.medical.model.dto;

import com.Olaf0.medical.model.entity.Doctor;
import com.Olaf0.medical.model.entity.Visit;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class HospitalDto {
    private Long id;
    private String name;
    private List<Doctor> doctors;
    private List<Visit> visits;

}

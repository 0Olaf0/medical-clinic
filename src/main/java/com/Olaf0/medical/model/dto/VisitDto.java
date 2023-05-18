package com.Olaf0.medical.model.dto;

import com.Olaf0.medical.model.entity.Hospital;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class VisitDto {
    private Hospital hospital;
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime term;
    private String reasonOfVisit;
    private String diagnosis;
    private String treatment;
}

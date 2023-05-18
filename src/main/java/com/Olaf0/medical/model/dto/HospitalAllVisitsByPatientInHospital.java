package com.Olaf0.medical.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAllVisitsByPatientInHospital {
    private Long hospitalId;
    private Long patientId;
    private String hospitalName;
}

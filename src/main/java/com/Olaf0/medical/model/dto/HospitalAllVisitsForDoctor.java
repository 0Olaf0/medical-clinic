package com.Olaf0.medical.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAllVisitsForDoctor {
    private Long hospitalId;
    private Long doctorId;
    private String hospitalName;
}

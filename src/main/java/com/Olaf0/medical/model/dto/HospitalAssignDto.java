package com.Olaf0.medical.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalAssignDto {
    private Long hospitalId;
    private Long doctorId;

}

package com.Olaf0.medical.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAssignRequestDto {
   private String doctorEmail;
   private String hospitalName;

}

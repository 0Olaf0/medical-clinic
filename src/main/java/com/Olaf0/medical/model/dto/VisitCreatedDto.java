package com.Olaf0.medical.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class VisitCreatedDto  {
    private Long doctorId;
    private LocalDateTime term;


}

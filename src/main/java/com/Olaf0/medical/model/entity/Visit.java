package com.Olaf0.medical.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Patient patient;
    private Long doctorId;
    private LocalDateTime term;
    private String reasonOfVisit;
    private String diagnosis;
    private String treatment;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="HOSPITAL_ID")
    private Hospital hospital;


    public void update(Visit editInfo) {
        this.setDiagnosis(editInfo.getDiagnosis());
        this.setTerm(editInfo.getTerm());
        this.setTreatment(editInfo.getTreatment());
        this.setReasonOfVisit(editInfo.getReasonOfVisit());
        this.setDoctorId(getDoctorId());

    }

}

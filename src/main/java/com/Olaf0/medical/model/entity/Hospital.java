package com.Olaf0.medical.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
public class Hospital {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy="hospital")
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital")
    private List<Visit> visits;
    @OneToMany(mappedBy = "hospital")
    private List<Patient> patients;

}

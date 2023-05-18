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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;




    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "Doctor_Patient",
            joinColumns = { @JoinColumn(name = "doctor_id") },
            inverseJoinColumns = { @JoinColumn(name = "patient_id") }
    )
    private List<Patient> patients;
    @ManyToOne
    private Hospital hospital;

}

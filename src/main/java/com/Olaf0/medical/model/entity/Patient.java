package com.Olaf0.medical.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String idCardNo;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;
    //Jeden doktor moze byc przypisany do wielu pacjentow

    @ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "Doctor_Patient",
            joinColumns = { @JoinColumn(name = "patient_id") },
            inverseJoinColumns = { @JoinColumn(name = "doctor_id") }
    )
    private List<Doctor> doctors;
    @ManyToOne
    private Hospital hospital;
    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;


    public void update(Patient editInfo) {
        this.setEmail(editInfo.getEmail());
        this.setPassword(editInfo.getPassword());
        this.setFirstName(editInfo.getFirstName());
        this.setLastName(editInfo.getLastName());
        this.setBirthday(editInfo.getBirthday());
        this.setPhoneNumber(editInfo.getPhoneNumber());
    }

}

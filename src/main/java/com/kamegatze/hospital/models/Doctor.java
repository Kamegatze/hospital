package com.kamegatze.hospital.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Essence{
    @Setter
    @Column(name = "first_name")
    private String firstName;
    @Setter
    @Column(name = "last_name")
    private String lastName;
    @Setter
    @Column(name = "patronymic")
    private String patronymic;
    @Setter
    @Column(name = "post")
    private String post;
    @Setter
    @Column(name = "job_time_begin")
    private Time jobTimeBegin;
    @Setter
    @Column(name = "job_time_end")
    private Time jobTimeEnd;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients = new ArrayList<>();


    public void setPatients(List<Patient> patients) {
        if(this.patients == null) {
            this.patients = patients;
            return;
        }
        this.patients.addAll(patients);

        for(Patient patient:patients) {
            patient.getDoctors().add(this);
        }
    }

    public void setPatient(Patient patient) {
        if(this.patients == null) {
            this.patients = new ArrayList<>(List.of(patient));
            return;
        }

        patient.getDoctors().add(this);
    }

    public void removePatient(Patient patient) {
        this.patients.remove(patient);
        patient.getDoctors().remove(this);
    }
}
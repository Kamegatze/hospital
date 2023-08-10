package com.kamegatze.hospital.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "Doctor")
public class Doctor extends Essence{
    @Setter
    @Column(name = "name")
    private String name;
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
    @JoinTable(name = "Doctor_Patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients;

    public Doctor() {
    }

    public Doctor(int id, String name, String post, Time jobTimeBegin, Time jobTimeEnd, List<Patient> patients) {
        this.setId(id);
        this.name = name;
        this.post = post;
        this.jobTimeBegin = jobTimeBegin;
        this.jobTimeEnd = jobTimeEnd;
        this.patients = patients;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor )) return false;
        return getId() != null && getId().equals( ((Doctor) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPost(), getJobTimeBegin(), getJobTimeEnd(), getPatients());
    }
}
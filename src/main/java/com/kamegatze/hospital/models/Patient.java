package com.kamegatze.hospital.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "Patient")
public class Patient extends Essence{
    @Setter
    @Column(name = "name")
    private String name;
    @Setter
    @Column(name = "disease")
    private String disease;
    @Setter
    @Column(name = "age")
    private long age;
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors;

    public Patient() {
    }

    public Patient(int id, String name, String disease, int age, List<Doctor> doctors) {
        this.setId(id);
        this.name = name;
        this.disease = disease;
        this.age = age;
        this.doctors = doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        if (this.doctors == null) {
            this.doctors = doctors;
            return;
        }
        this.doctors.addAll(doctors);

        for (Doctor doctor:doctors) {
            doctor.getPatients().add(this);
        }
    }
    public void setDoctor(Doctor doctor) {
        if (this.doctors == null) {
            this.doctors = new ArrayList<>(List.of(doctor));
            return;
        }
        doctors.add(doctor);
        doctor.getPatients().add(this);
    }

    public void removeDoctor(Doctor doctor) {
        this.doctors.remove(doctor);
        doctor.getPatients().remove(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        return getId() != null && getId().equals(((Patient) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDisease(), getAge(), getDoctors());
    }
}
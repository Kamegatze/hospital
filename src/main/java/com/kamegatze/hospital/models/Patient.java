package com.kamegatze.hospital.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "Patient")
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends Essence{
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
    @Column(name = "disease")
    private String disease;
    @Setter
    @Column(name = "age")
    private long age;
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors;


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

}
package com.kamegatze.hospital.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@Table(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Doctor extends Essence{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "post")
    private String post;

    @Column(name = "job_time_begin")
    private LocalTime jobTimeBegin;

    @Column(name = "job_time_end")
    private LocalTime jobTimeEnd;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients = new ArrayList<>();

}
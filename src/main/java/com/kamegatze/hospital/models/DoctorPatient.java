package com.kamegatze.hospital.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor_patient")
public class DoctorPatient extends Essence{


    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "patient_id")
    private Integer patientId;

}

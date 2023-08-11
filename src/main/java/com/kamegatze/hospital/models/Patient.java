package com.kamegatze.hospital.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@Table(name = "Patient")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends Essence{
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "patronymic")
    private String patronymic;
    
    @Column(name = "disease")
    private String disease;
    
    @Column(name = "age")
    private int age;
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors = new ArrayList<>();
    
}
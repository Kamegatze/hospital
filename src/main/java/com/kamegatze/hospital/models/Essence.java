package com.kamegatze.hospital.models;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@MappedSuperclass
public class Essence {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}

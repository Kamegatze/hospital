package com.kamegatze.hospital.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    
    private int id;
    
    private String firstName;
    
    private String lastName;
    
    private String patronymic;
    
    private String disease;
    
    private int age;
}

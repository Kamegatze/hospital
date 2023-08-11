package com.kamegatze.hospital.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    
    private int id;
    
    private String firstName;
    
    private String lastName;
    
    private String patronymic;
    
    private String post;
    
    private Time jobTimeBegin;
    
    private Time jobTimeEnd;
}

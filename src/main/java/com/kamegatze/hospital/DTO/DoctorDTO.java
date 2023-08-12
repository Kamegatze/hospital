package com.kamegatze.hospital.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    
    private int id;

    @NotNull(message = "Field firstName must not be null")
    @NotEmpty(message = "Field firstName must not be empty")
    @Size(min = 2, message = "Field firstName must be more 1 sign")
    private String firstName;

    @NotNull(message = "Field lastName must not be null")
    @NotEmpty(message = "Field lastName must not be empty")
    @Size(min = 2, message = "Field lastName must be more 1 sign")
    private String lastName;

    @NotNull(message = "Field patronymic must not be null")
    @NotEmpty(message = "Field patronymic must not be empty")
    @Size(min = 2, message = "Field patronymic must be more 1 sign")
    private String patronymic;

    @NotNull(message = "Field patronymic must not be null")
    @NotEmpty(message = "Field patronymic must not be empty")
    @Size(min = 4, message = "Field patronymic must be more 3 sign")
    private String post;


    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jobTimeBegin;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jobTimeEnd;
}

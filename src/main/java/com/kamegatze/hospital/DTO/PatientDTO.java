package com.kamegatze.hospital.DTO;

import jakarta.validation.constraints.*;
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

    @NotNull(message = "Field patronymic must not be null")
    @NotEmpty(message = "Field patronymic must not be empty")
    @Size(min = 2, message = "Field patronymic must be more 1 sign")
    private String firstName;

    @NotNull(message = "Field patronymic must not be null")
    @NotEmpty(message = "Field patronymic must not be empty")
    @Size(min = 2, message = "Field patronymic must be more 1 sign")
    private String lastName;

    @NotNull(message = "Field patronymic must not be null")
    @NotEmpty(message = "Field patronymic must not be empty")
    @Size(min = 2, message = "Field patronymic must be more 1 sign")
    private String patronymic;

    @NotNull(message = "Field patronymic must not be null")
    @NotEmpty(message = "Field patronymic must not be empty")
    @Size(min = 5, message = "Field patronymic must be more 4 sign")
    private String disease;

    @Min(value = 0, message = "Field age must be begin with 0")
    @Max(value = 100, message = "Field age must be less 101")
    @NotNull(message = "Field patronymic must not be null")
    private int age;
}

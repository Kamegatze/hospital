package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.DTO.PatientDTOList;
import com.kamegatze.hospital.DTO.Response;
import com.kamegatze.hospital.DTO.EStatus;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.models.Patient;
import com.kamegatze.hospital.servisies.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/")
    public ResponseEntity<List<PatientDTOList>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTOList> getById(
            @Min(value = 1, message = "id must be more 0") @PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientService.getPatient(id));
    }

    @PostMapping("/")
    public ResponseEntity<Response> addPatient(
            @Valid @RequestBody PatientDTO patient, UriComponentsBuilder uri) {

        Patient patientSave = patientService.addAndUpdatePatient(patient);

        Response response = Response.builder()
                .message("Patient was created")
                .status(EStatus.STATUS_CREATED.geStatus())
                .build();

        return ResponseEntity.created(
                    uri.path("/patients/{id}")
                            .build(Map.of("id", patientSave.getId()))
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/")
    public ResponseEntity<Response> updatePatient(@Valid @RequestBody PatientDTO patient) {
        patientService.addAndUpdatePatient(patient);

        Response response = Response.builder()
                .message("Patient was updated")
                .status(EStatus.STATUS_UPDATED.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removePatient(
            @Min(value = 1, message = "id must be more 0") @PathVariable Integer id)
            throws UserNotFoundException {

        patientService.removePatient(id);

        Response response = Response.builder()
                .message("Patient was deleted")
                .status(EStatus.STATUS_DELETED.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

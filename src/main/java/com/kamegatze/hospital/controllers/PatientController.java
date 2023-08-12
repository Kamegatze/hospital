package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.DTO.PatientDTOList;
import com.kamegatze.hospital.DTO.Response;
import com.kamegatze.hospital.DTO.EStatus;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.servisies.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<PatientDTOList> getById(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientService.getPatient(id));
    }

    @PostMapping("/")
    public ResponseEntity<Response> addPatient(@RequestBody PatientDTO patient) {
        patientService.addAndUpdatePatient(patient);

        Response response = Response.builder()
                .message("Patient was created")
                .status(EStatus.STATUS_CREATED)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/")
    public ResponseEntity<Response> updatePatient(@RequestBody PatientDTO patient) {
        patientService.addAndUpdatePatient(patient);

        Response response = Response.builder()
                .message("Patient was updated")
                .status(EStatus.STATUS_UPDATED)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removePatient(@PathVariable Integer id) {
        patientService.removePatient(id);

        Response response = Response.builder()
                .message("Patient was deleted")
                .status(EStatus.STATUS_DELETED)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

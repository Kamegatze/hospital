package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.DTO.PatientDTOList;
import com.kamegatze.hospital.servisies.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public List<PatientDTOList> getAll() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public PatientDTOList getById(@PathVariable int id) {
        return patientService.getById(id);
    }

    @GetMapping("/last")
    public PatientDTOList lastPatient() {
        return this.patientService.lastPatient();
    }

    @PostMapping("/")
    public void addPatient(@RequestBody PatientDTO patient) throws Exception {
        patientService.addAndUpdatePatient(patient);
    }

    @PutMapping("/")
    public void updatePatient(@RequestBody PatientDTO patient) throws Exception {
        patientService.addAndUpdatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void removePatient(@PathVariable Integer id) {
        patientService.removePatient(id);
    }

    @GetMapping("/addition-reception")
    public ResponseEntity<?> handleAdditionReception(@RequestParam Integer doctorId, @RequestParam Integer patientId) throws Exception {

        this.patientService.handleAdditionReception(doctorId, patientId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("message", "addition reception created!!!"));
    }

    @GetMapping("/cancel-of-reception")
    public ResponseEntity<?> handleCancellationOfReception(@RequestParam Integer doctorId, @RequestParam Integer patientId) throws Exception {

        this.patientService.handleCancellationOfReception(doctorId, patientId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("message", "Reception was cancel!!!"));
    }
}

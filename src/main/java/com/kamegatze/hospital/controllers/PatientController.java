package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.PatientDTO;
import com.kamegatze.hospital.models.Patient;
import com.kamegatze.hospital.servisies.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<PatientDTO> getAll() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public PatientDTO getById(@PathVariable int id) {
        return patientService.getById(id);
    }

    @GetMapping("/last")
    public Patient lastPatient() {
        return this.patientService.lastPatient();
    }

    @PostMapping()
    public void addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

    @PutMapping()
    public void updatePatient(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }

    @DeleteMapping()
    public void removePatient(@RequestBody Patient patient) {
        patientService.removePatient(patient);
    }
}

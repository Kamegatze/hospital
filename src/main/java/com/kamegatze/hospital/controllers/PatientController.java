package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.PatientDTOList;
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

    @PostMapping()
    public void addPatient(@RequestBody PatientDTOList patient) throws Exception {
        patientService.addAndUpdatePatient(patient);
    }

    @PutMapping()
    public void updatePatient(@RequestBody PatientDTOList patient) throws Exception {
        patientService.addAndUpdatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void removePatient(@PathVariable Integer id) {
        patientService.removePatient(id);
    }
}

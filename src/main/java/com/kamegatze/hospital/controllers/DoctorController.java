package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.DoctorDTO;
import com.kamegatze.hospital.servisies.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kamegatze.hospital.models.Doctor;
import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public List<DoctorDTO> getAll() {
        return doctorService.getAll();
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctor(@PathVariable int id) {
        return doctorService.getDoctor(id);
    }

    @GetMapping("/last")
    public Doctor getLastDoctor() {
        return this.doctorService.lastDoctor();
    }

    @GetMapping("/post")
    public DoctorDTO getDoctorByPost(@RequestParam String post) throws Exception {
        return this.doctorService.findDoctorByPost(post);
    }

    @PostMapping()
    public void addDoctor(@RequestBody Doctor doctor) {
        this.doctorService.addDoctor(doctor);
    }

    @PutMapping()
    public void updateDoctor(@RequestBody Doctor doctor) {
        this.doctorService.updateDoctor(doctor);
    }

    @DeleteMapping()
    public void deleteDoctor(@RequestBody Doctor doctor) {
        this.doctorService.deleteDoctor(doctor);
    }
}

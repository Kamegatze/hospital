package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.DoctorDTO;
import com.kamegatze.hospital.DTO.DoctorDTOList;
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

    @GetMapping("/")
    public List<DoctorDTOList> getAll() {
        return doctorService.getAll();
    }

    @GetMapping("/{id}")
    public DoctorDTOList getDoctor(@PathVariable int id) {
        return doctorService.getDoctor(id);
    }

    @GetMapping("/last")
    public DoctorDTOList getLastDoctor() {
        return this.doctorService.lastDoctor();
    }

    @GetMapping("/post")
    public DoctorDTOList getDoctorByPost(@RequestParam String post) throws Exception {
        return this.doctorService.findDoctorByPost(post);
    }

    @PostMapping("/")
    public void addDoctor(@RequestBody DoctorDTO doctor) {
        this.doctorService.addAndUpdateDoctor(doctor);
    }

    @PutMapping("/")
    public void updateDoctor(@RequestBody DoctorDTO doctor) {
        this.doctorService.addAndUpdateDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Integer id) {
        this.doctorService.deleteDoctor(id);
    }
}

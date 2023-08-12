package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.DoctorDTO;
import com.kamegatze.hospital.DTO.DoctorDTOList;
import com.kamegatze.hospital.DTO.EStatus;
import com.kamegatze.hospital.DTO.Response;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.models.Doctor;
import com.kamegatze.hospital.servisies.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<List<DoctorDTOList>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.doctorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTOList> getDoctor(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(doctorService.getDoctor(id));
    }

    @PostMapping("/")
    public ResponseEntity<Response> addDoctor(
            @RequestBody DoctorDTO doctor, UriComponentsBuilder uri) {


        Doctor doctorSave = this.doctorService.addAndUpdateDoctor(doctor);

        Response response = Response.builder()
                .message("Doctor was created")
                .status(EStatus.STATUS_CREATED.geStatus())
                .build();



        return ResponseEntity.created(
                uri.path("/doctors/{id}")
                        .build(Map.of("id", doctorSave.getId()))
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/")
    public ResponseEntity<Response> updateDoctor(@RequestBody DoctorDTO doctor) {

        this.doctorService.addAndUpdateDoctor(doctor);

        Response response = Response.builder()
                .message("Doctor was updated")
                .status(EStatus.STATUS_UPDATED.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDoctor(@PathVariable Integer id) {
        this.doctorService.deleteDoctor(id);

        Response response = Response.builder()
                .message("Doctor was deleted")
                .status(EStatus.STATUS_DELETED.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.EStatus;
import com.kamegatze.hospital.DTO.Response;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.servisies.ReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reception")
public class ReceptionController {

    private final ReceptionService receptionService;

    @GetMapping("/addition-reception")
    public ResponseEntity<Response> handleAdditionReception(
            @RequestParam Integer doctorId, @RequestParam Integer patientId)
            throws UserNotFoundException {

        this.receptionService.handleAdditionReception(doctorId, patientId);

        Response response = Response.builder()
                .message("Patient writing on reception")
                .status(EStatus.STATUS_RECEPTION_WRITING)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/cancel-of-reception")
    public ResponseEntity<Response> handleCancellationOfReception(
            @RequestParam Integer doctorId, @RequestParam Integer patientId)
            throws UserNotFoundException {

        this.receptionService.handleCancellationOfReception(doctorId, patientId);

        Response response = Response.builder()
                .message("Reception was canceled")
                .status(EStatus.STATUS_RECEPTION_CANCELED)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

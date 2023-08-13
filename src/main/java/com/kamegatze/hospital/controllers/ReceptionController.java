package com.kamegatze.hospital.controllers;

import com.kamegatze.hospital.DTO.EStatus;
import com.kamegatze.hospital.DTO.Response;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import com.kamegatze.hospital.servisies.ReceptionService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/reception")
public class ReceptionController {

    private static Logger log = Logger.getLogger(ReceptionController.class.getName());

    private final ReceptionService receptionService;

    @GetMapping("/addition-reception")
    public ResponseEntity<Response> handleAdditionReception(
            @Min(value = 1, message = "id must be more 0") @RequestParam Integer doctorId,
            @Min(value = 1, message = "id must be more 0") @RequestParam Integer patientId)
            throws UserNotFoundException {

        log.warning("begin addition relationship between doctor and patient");

        this.receptionService.handleAdditionReception(doctorId, patientId);

        log.info("end addition relationship between doctor and patient");

        Response response = Response.builder()
                .message("Patient writing on reception")
                .status(EStatus.STATUS_RECEPTION_WRITING.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/cancel-of-reception")
    public ResponseEntity<Response> handleCancellationOfReception(
            @Min(value = 1, message = "id must be more 0") @RequestParam Integer doctorId,
            @Min(value = 1, message = "id must be more 0") @RequestParam Integer patientId)
            throws UserNotFoundException {

        log.warning("begin delete relationship between doctor and patient");

        this.receptionService.handleCancellationOfReception(doctorId, patientId);

        log.warning("end delete relationship between doctor and patient");

        Response response = Response.builder()
                .message("Reception was canceled")
                .status(EStatus.STATUS_RECEPTION_CANCELED.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

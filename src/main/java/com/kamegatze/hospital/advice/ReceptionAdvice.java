package com.kamegatze.hospital.advice;


import com.kamegatze.hospital.DTO.EStatus;
import com.kamegatze.hospital.DTO.Response;
import com.kamegatze.hospital.controllers.ReceptionController;
import com.kamegatze.hospital.custom_exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ReceptionController.class)
public class ReceptionAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handlerExceptionOfUserNotFound(UserNotFoundException exception) {
        Response response = Response.builder()
                .message(exception.getMessage())
                .status(EStatus.STATUS_NOT_FOUND.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleValidException(ConstraintViolationException exception) {

        Response response = Response.builder()
                .message(exception.getMessage())
                .status(EStatus.STATUS_FAILED_VALIDATION.geStatus())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

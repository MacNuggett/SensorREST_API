package com.firstRestApp.controllers;

import com.firstRestApp.dto.MeasurementDTO;
import com.firstRestApp.models.Measurement;
import com.firstRestApp.services.MeasurementService;
import com.firstRestApp.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public List<MeasurementDTO> findAll(){
        return measurementService.getMeasurements();
    }

    @GetMapping("/getRainyDays")
    public int getRainyDays(){
        return measurementService.getRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid MeasurementDTO measurementDTO,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder error = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError fieldError:errors)
                error.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");

            throw new MeasurementNotAddedException((error.toString()));
        }
        measurementService.save(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddedException measurementNotAddedException){
        MeasurementErrorResponse measurementErrorResponse =
                new MeasurementErrorResponse("Measurement was NOT added: "+measurementNotAddedException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }
}

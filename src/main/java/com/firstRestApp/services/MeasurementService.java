package com.firstRestApp.services;

import com.firstRestApp.dto.MeasurementDTO;
import com.firstRestApp.dto.SensorDTO;
import com.firstRestApp.models.Measurement;
import com.firstRestApp.models.Sensor;
import com.firstRestApp.repositories.MeasurementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, ModelMapper modelMapper, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    public void save(MeasurementDTO measurementDTO){
        Sensor sensor = sensorService.getSensorByName(measurementDTO.getSensor().getName());
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setSensor(sensor);
        measurement.setMeasuredAt(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<MeasurementDTO> getMeasurements(){
        return measurementRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public int getRainyDays(){
        List<Measurement> rainyDays = measurementRepository.findByIsRaining(true);
        return rainyDays.size();
    }

    private MeasurementDTO convertToDto(Measurement measurement){
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensorDTO(modelMapper.map(measurement.getSensor(), SensorDTO.class));
        return measurementDTO;
    }
}

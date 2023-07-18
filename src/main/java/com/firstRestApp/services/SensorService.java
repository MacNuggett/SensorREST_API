package com.firstRestApp.services;

import com.firstRestApp.dto.SensorDTO;
import com.firstRestApp.models.Sensor;
import com.firstRestApp.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public void register(SensorDTO sensorDTO){
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        sensor.setCreatedAt(LocalDateTime.now());
        sensorRepository.save(sensor);
    }

    public Sensor getSensorByName(String name){
        return sensorRepository.findByName(name);
    }
}

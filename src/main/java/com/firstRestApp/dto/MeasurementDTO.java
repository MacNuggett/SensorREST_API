package com.firstRestApp.dto;

import jakarta.validation.constraints.*;

public class MeasurementDTO {
    @DecimalMax(value = "50.0", message = "Максимальное значение: 50.0")
    @DecimalMin(value = "-50.0", message = "Минимальное значение: -50.0")
    @NotNull(message = "value не может быть пустым")
    private double value;

    @NotNull(message = "isRaining не может быть пустым")
    private boolean isRaining;

    //@ManyToOne
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getIsRaining() {
        return this.isRaining;
    }

    public void setIsRaining(boolean isRaining) {
        this.isRaining = isRaining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensorDTO(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", isRaining=" + isRaining +
                ", sensor=" + sensor +
                '}';
    }
}

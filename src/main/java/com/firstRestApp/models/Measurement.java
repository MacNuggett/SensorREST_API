package com.firstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @DecimalMax(value = "50.0", message = "Максимальное значение: 50.0")
    @DecimalMin(value = "-50.0", message = "Минимальное значение: -50.0")
    @NotNull(message = "value не может быть пустым")
    private double value;

    @Column(name = "is_raining")
    @NotNull(message = "isRaining не может быть пустым")
    private boolean isRaining;

    @Column(name = "measured_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime measuredAt;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(double value, boolean isRaining) {
        this.value = value;
        this.isRaining = isRaining;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getIsRaining() {
        return isRaining;
    }

    public void setIsRaining(boolean isRaining) {
        this.isRaining = isRaining;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(LocalDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", sensor=" + sensor +
                '}';
    }
}

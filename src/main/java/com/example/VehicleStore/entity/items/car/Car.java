package com.example.VehicleStore.entity.items.car;

import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.items.car.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "car")
@Accessors(chain = true)
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_brand_id", nullable = false)
    private CarBrand carBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_engine_id", nullable = false)
    private Engine engine;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_condition", nullable = false)
    private Condition condition;

    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;

    @Column(name = "rental_price", nullable = false)
    private Integer rentalPrice;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "mileage", nullable = false)
    private Double mileage;

    @Column(name = "color", nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", nullable = false)
    private Transmission transmissionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "drivetrain", nullable = false)
    private Drivetrain drivetrain;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_bodywork", nullable = false)
    private CarBodyWork carBodyWork;

    @Enumerated(EnumType.STRING)
    @Column(name = "number_of_seats", nullable = false)
    private Seats numberOfSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "number_of_doors", nullable = false)
    private Doors numberOfDoors;

    @Enumerated(EnumType.STRING)
    @Column(name = "steering_wheel_side")
    private Steering steeringWheelSide;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}

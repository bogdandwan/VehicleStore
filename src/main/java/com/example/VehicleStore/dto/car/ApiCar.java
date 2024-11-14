package com.example.VehicleStore.dto.car;

import com.example.VehicleStore.dto.engine.ApiEngine;
import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.items.car.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiCar {

    private Long id;
    private ApiCarBrand carBrand;
    private ApiCarModel carModel;
    private ApiEngine engine;
    private Condition condition;
    private Integer purchasePrice;
    private Integer rentalPrice;
    private Integer year;
    private Double mileage;
    private String color;
    private Transmission transmissionType;
    private Drivetrain drivetrain;
    private CarBodyWork carBodyWork;
    private Seats numberOfSeats;
    private Doors numberOfDoors;
    private Steering steeringWheelSide;
    private Boolean available;

    public ApiCar(Car car){
        id = car.getId();
        condition = car.getCondition();
        purchasePrice = car.getPurchasePrice();
        rentalPrice = car.getRentalPrice();
        year = car.getYear();
        mileage = car.getMileage();
        color = car.getColor();
        transmissionType = car.getTransmissionType();
        drivetrain = car.getDrivetrain();
        carBodyWork = car.getCarBodyWork();
        numberOfSeats = car.getNumberOfSeats();
        numberOfDoors = car.getNumberOfDoors();
        steeringWheelSide = car.getSteeringWheelSide();
        available = car.getAvailable();


        if (car.getCarBrand() != null){
            carBrand = new ApiCarBrand(car.getCarBrand());
        }
        if (car.getCarModel() != null){
            carModel = new ApiCarModel(car.getCarModel());
        }
        if (car.getEngine() != null){
            engine = new ApiEngine(car.getEngine());
        }
    }

}

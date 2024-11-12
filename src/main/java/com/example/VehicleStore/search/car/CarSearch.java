package com.example.VehicleStore.search.car;

import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.items.car.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CarSearch {

    private CarBrand carBrand;
    private CarModel carModel;
    private Engine engine;
    private Condition carCondition;
    private Fuel fuel;
    private Integer purchasePrice;
    private Integer purchasePriceFrom;
    private Integer purchasePriceTo;
    private Integer rentalPrice;
    private Integer rentalPriceFrom;
    private Integer rentalPriceTo;
    private Integer year;
    private Double mileage;
    private Double mileageFrom;
    private Double mileageTo;
    private Integer yearFrom;
    private Integer yearTo;
    private String color;
    private Transmission transmissionType;
    private Drivetrain drivetrain;
    private CarBodyWork carBodyWork;
    private Seats numberOfSeats;
    private Doors numberOfDoors;
    private Steering steeringWheelSide;
    private Boolean available;
}

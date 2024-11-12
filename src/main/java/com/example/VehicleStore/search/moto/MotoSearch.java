package com.example.VehicleStore.search.moto;

import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.moto.enums.Transmission;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.entity.items.moto.enums.Seats;
import com.example.VehicleStore.entity.items.moto.enums.TypeBodyWork;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MotoSearch {


    private MotoBrand motoBrand;
    private MotoModel motoModel;
    private Engine engine;
    private Fuel fuel;
    private Condition condition;
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
    private TypeBodyWork typeBodyWork;
    private Seats nubmerOfSeats;
    private Boolean available;


}

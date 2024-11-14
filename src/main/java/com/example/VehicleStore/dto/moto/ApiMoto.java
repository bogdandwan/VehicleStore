package com.example.VehicleStore.dto.moto;

import com.example.VehicleStore.dto.engine.ApiEngine;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.items.moto.enums.Seats;
import com.example.VehicleStore.entity.items.moto.enums.Transmission;
import com.example.VehicleStore.entity.items.moto.enums.TypeBodyWork;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiMoto {

    private Long id;
    private ApiMotoBrand motoBrand;
    private ApiMotoModel motoModel;
    private ApiEngine engine;
    private Condition condition;
    private Integer purchasePrice;
    private Integer rentalPrice;
    private Integer year;
    private Double mileage;
    private String color;
    private Transmission transmissionType;
    private TypeBodyWork typeBodyWork;
    private Seats seats;
    private Boolean available;

    public ApiMoto (Motorcycle motorcycle){
        id = motorcycle.getId();
        condition = motorcycle.getCondition();
        purchasePrice = motorcycle.getPurchasePrice();
        rentalPrice = motorcycle.getRentalPrice();
        year = motorcycle.getYear();
        transmissionType = motorcycle.getTransmissionType();
        typeBodyWork = motorcycle.getTypeBodyWork();
        seats = motorcycle.getSeats();
        available = motorcycle.getAvailable();

        if (motoBrand != null){
            motoBrand = new ApiMotoBrand(motorcycle.getMotoBrand());
        }
        if (motoModel != null){
            motoModel = new ApiMotoModel(motorcycle.getMotoModel());
        }
        if (engine != null ){
            engine = new ApiEngine(motorcycle.getEngine());
        }
    }

}

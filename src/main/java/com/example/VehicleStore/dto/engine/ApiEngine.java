package com.example.VehicleStore.dto.engine;

import com.example.VehicleStore.dto.car.ApiCar;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.engine.enums.EmissionStandard;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApiEngine {

    private Long id;
    private Fuel fuel;
    private Integer displacement;
    private Double kwPower;
    private Integer horsePower;
    private EmissionStandard emissionStandard;
    private Integer numberOfCylinders;
    private Double fuelConsumption;
    private List<ApiCar> cars;

    public ApiEngine(Engine engine){
        id = engine.getId();
        fuel = engine.getFuel();
        displacement = engine.getDisplacement();
        kwPower = engine.getKwPower();
        horsePower = engine.getHorsePower();
        emissionStandard = engine.getEmissionStandard();
        numberOfCylinders = engine.getNumberOfCylinders();
        fuelConsumption = engine.getFuelConsumption();
    }

}

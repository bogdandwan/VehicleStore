package com.example.VehicleStore.search.engine;

import com.example.VehicleStore.entity.items.engine.enums.EmissionStandard;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class EngineSearch {

    private Fuel fuel;
    private Integer displacement;
    private Integer displacementFrom;
    private Integer displacementTo;
    private Double kwPower;
    private Double kwPowerFrom;
    private Double kwPowerTo;
    private Integer horsePower;
    private Integer horsePowerFrom;
    private Integer horsePowerTo;
    private EmissionStandard emissionStandard;
    private Integer numberOfCylinders;
    private Double fuelConsumption;
    private Double fuelConsumptionFrom;
    private Double fuelConsumptionTo;


}

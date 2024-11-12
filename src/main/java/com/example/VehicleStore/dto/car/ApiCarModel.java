package com.example.VehicleStore.dto.car;

import com.example.VehicleStore.entity.items.car.CarModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiCarModel {

    private Long id;
    private String name;
    private ApiCarBrand carBrand;


    public ApiCarModel(CarModel carModel){
        id = carModel.getId();
        name = carModel.getName();
    }

}

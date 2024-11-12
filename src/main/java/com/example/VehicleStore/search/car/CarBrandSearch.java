package com.example.VehicleStore.search.car;

import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.sort.car.CarBrandSort;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CarBrandSearch {

    private String name;
    private String brandCountry;
    private CarModel carModel;
    private Boolean available;

}

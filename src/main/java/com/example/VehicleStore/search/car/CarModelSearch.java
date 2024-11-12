package com.example.VehicleStore.search.car;

import com.example.VehicleStore.entity.items.car.CarBrand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CarModelSearch {

    private String name;
    private CarBrand carBrand;
    private Boolean available;

}

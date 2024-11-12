package com.example.VehicleStore.dto.car;

import com.example.VehicleStore.entity.items.car.CarBrand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ApiCarBrand {

    private Long id;
    private String name;
    private String brandCountry;
    private List<ApiCarModel> carModels;
    private Boolean available;

    public ApiCarBrand(CarBrand carBrand){
        id = carBrand.getId();
        name = carBrand.getName();
        brandCountry = carBrand.getBrandCountry();
        available = carBrand.getAvailable();
        if (carBrand.getCarModels() != null) {
            carModels = carBrand.getCarModels().stream()
                    .map(ApiCarModel::new)
                    .collect(Collectors.toList());
        }
    }

}

package com.example.VehicleStore.dto.moto;

import com.example.VehicleStore.entity.items.moto.MotoBrand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ApiMotoBrand {

    private Long id;
    private String name;
    private String brandCountry;
    private List<ApiMotoModel> motoModels;
    private Boolean available;

    public ApiMotoBrand(MotoBrand motoBrand){
        id = motoBrand.getId();
        name = motoBrand.getName();
        brandCountry = motoBrand.getBrandCountry();
        available = motoBrand.getAvailable();
        if (motoBrand.getMotoModels() != null) {
            motoModels = motoBrand.getMotoModels().stream()
                    .map(ApiMotoModel::new)
                    .collect(Collectors.toList());
        }
    }

}

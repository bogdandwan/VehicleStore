package com.example.VehicleStore.dto.moto;

import com.example.VehicleStore.entity.items.moto.MotoModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApiMotoModel {

    private Long id;
    private String name;
    private ApiMotoBrand motoBrand;

    public ApiMotoModel(MotoModel motoModel){
        id = motoModel.getId();
        name = motoModel.getName();
    }

}

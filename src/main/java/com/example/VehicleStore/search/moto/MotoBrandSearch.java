package com.example.VehicleStore.search.moto;

import com.example.VehicleStore.entity.items.moto.MotoModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MotoBrandSearch {

    private String name;
    private String brandCountry;
    private MotoModel motoModel;
    private Boolean available;

}

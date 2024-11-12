package com.example.VehicleStore.search.moto;

import com.example.VehicleStore.entity.items.moto.MotoBrand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MotoModelSearch {

    private String name;
    private MotoBrand motoBrand;
    private Boolean available;

}

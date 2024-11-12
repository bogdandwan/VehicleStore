package com.example.VehicleStore.search.user;

import com.example.VehicleStore.entity.user.enums.Privilege;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RoleSearch {

    private String name;
    private Privilege privilege;

}

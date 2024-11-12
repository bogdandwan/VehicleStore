package com.example.VehicleStore.dto.user;

import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.enums.Privilege;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ApiRole {


    private Long id;
    private String name;
    private List<Privilege> privileges;


    public ApiRole(Role role) {

            id = role.getId();
            name = role.getName();
            privileges = role.getPrivileges();

    }
}

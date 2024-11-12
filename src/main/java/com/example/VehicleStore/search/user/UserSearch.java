package com.example.VehicleStore.search.user;

import com.example.VehicleStore.entity.user.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserSearch {

    private String firstNameLike;
    private String lastNameLike;
    private String email;
    private Role role;

}

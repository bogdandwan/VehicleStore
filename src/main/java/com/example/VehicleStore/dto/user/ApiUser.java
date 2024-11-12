package com.example.VehicleStore.dto.user;

import com.example.VehicleStore.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApiUser {

    private Long id;
    @NotBlank private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ApiRole role;


    public ApiUser(User user){

            id = user.getId();
            firstName = user.getFirstName();
            lastName = user.getLastName();
            email = user.getEmail();
            password = user.getPassword();
            if (user.getRole() != null) {
                role = new ApiRole(user.getRole());
            }


    }

}

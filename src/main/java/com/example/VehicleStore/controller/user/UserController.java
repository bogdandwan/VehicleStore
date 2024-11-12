package com.example.VehicleStore.controller.user;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.dto.user.ApiUser;
import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.entity.user.sort.UserSort;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.user.UserSearch;
import com.example.VehicleStore.service.service.user.RoleService;
import com.example.VehicleStore.service.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final RoleService roleService;


    @PostMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void saveOrUpdateUser(@RequestBody @Valid ApiUser apiUser){
        final User user;

        if (apiUser.getId() == null){
            user = new User();
        }else {
            user = userService.findById(apiUser.getId());
            if (user == null){
                throw new NotFoundException("Car not found");
            }
        }

        if (apiUser.getFirstName() != null) {
            user.setFirstName(apiUser.getFirstName());
        }
        if (apiUser.getLastName() != null) {
            user.setLastName(apiUser.getLastName());
        }
        if (apiUser.getEmail() != null) {
            user.setEmail(apiUser.getEmail());
        }
        if (apiUser.getPassword() != null) {
            user.setPassword(apiUser.getPassword());
        }
        if (apiUser.getRole() != null) {
            user.setRole(roleService.findById(apiUser.getRole().getId()));
        }

        userService.save(user);
    }

    @GetMapping("/user/{id}")
    public ApiUser getUserById(@PathVariable Long id){
        final User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return new ApiUser(user);
    }



    @GetMapping("/users")
    public List<ApiUser> getAllUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) UserSort sort,
            Pagination pagination){

        final UserSearch search = new UserSearch()
                .setFirstNameLike(firstName)
                .setLastNameLike(lastName)
                .setEmail(email);

        if (roleId != null) {
            final Role role = roleService.findById(roleId);
            if (role == null) {
                throw new NotFoundException("Role not found");
            }
            search.setRole(role);
        }

        final List<User> users = userService.findAll(search, pagination, sort);
        return users.stream().map(ApiUser::new).collect(Collectors.toList());

    }



    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id){
        userService.softDelete(id);
    }

}

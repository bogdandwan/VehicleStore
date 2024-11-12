package com.example.VehicleStore.controller.user;

import com.example.VehicleStore.dto.user.ApiRole;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.enums.Privilege;
import com.example.VehicleStore.entity.user.sort.RoleSort;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.user.RoleSearch;
import com.example.VehicleStore.service.service.user.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RoleController {

    final RoleService roleService;


    @PostMapping("/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void saveOrUpdateRole(@RequestBody ApiRole apiRole){

        final Role role;

        if (apiRole.getId() == null) {
            role = new Role();
        }else {
            role = roleService.findById(apiRole.getId());
            if (role == null){
                throw new NotFoundException("Role not found,");
            }
        }
        if (apiRole.getName() != null) {
            role.setName(apiRole.getName());
        }
        if (apiRole .getPrivileges() != null) {
            role.setPrivileges(apiRole.getPrivileges());
        }

        roleService.save(role);

    }

    @GetMapping("/role/{id}")
    public ApiRole getById(@PathVariable Long id){
        Role role = roleService.findById(id);
        if(role == null){
            throw new NotFoundException("Role not found");
        }
        return new ApiRole(role);
    }



    @GetMapping("/roles")
    public List<ApiRole> getAll(
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Privilege privilege,
            @RequestParam (required = false) RoleSort sort,
            Pagination pagination){

        final RoleSearch search = new RoleSearch()
                .setName(name)
                .setPrivilege(privilege);

        final List<Role> roles = roleService.findAll(search, pagination, sort);

        return roles.stream().map(ApiRole::new).collect(Collectors.toList());

    }


    @DeleteMapping("/role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteRole(@PathVariable Long id){
        roleService.softDelete(id);
    }

}

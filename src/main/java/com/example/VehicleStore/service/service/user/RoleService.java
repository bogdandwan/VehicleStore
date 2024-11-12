package com.example.VehicleStore.service.service.user;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.sort.RoleSort;
import com.example.VehicleStore.search.user.RoleSearch;

import java.util.List;

public interface RoleService {

    Role findById(Long id);

    void save(Role role);

    List<Role> findAll(RoleSearch search, Pagination pagination, RoleSort sort);

    void softDelete(Long id);
}

package com.example.VehicleStore.service.service.user;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.entity.user.sort.UserSort;
import com.example.VehicleStore.search.user.UserSearch;

import java.util.List;

public interface UserService {

    User findById(Long id);

    void save(User user);

    List<User> findAll(UserSearch search, Pagination pagination, UserSort sort);

    void softDelete(Long id);

}

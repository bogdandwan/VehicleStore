package com.example.VehicleStore.repository.user;


import com.example.VehicleStore.entity.user.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll(Specification<Role> spec, Pageable pageable);

    Optional<Role> findByName(String name);
}

package com.example.VehicleStore.repository.user;

import com.example.VehicleStore.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll(Specification<User> spec, Pageable pageable);

    Optional<User> findByEmail(String email);

}

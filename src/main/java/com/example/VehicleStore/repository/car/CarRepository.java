package com.example.VehicleStore.repository.car;

import com.example.VehicleStore.entity.items.car.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAll(Specification<Car> spec, Pageable pageable);


}

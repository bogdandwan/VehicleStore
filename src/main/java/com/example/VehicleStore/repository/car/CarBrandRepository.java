package com.example.VehicleStore.repository.car;

import com.example.VehicleStore.entity.items.car.CarBrand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    List<CarBrand> findAll(Specification<CarBrand> spec, Pageable pageable);
}

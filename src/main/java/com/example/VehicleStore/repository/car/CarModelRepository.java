package com.example.VehicleStore.repository.car;

import com.example.VehicleStore.entity.items.car.CarModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    List<CarModel> findAll(Specification<CarModel> spec, Pageable pageable);

}

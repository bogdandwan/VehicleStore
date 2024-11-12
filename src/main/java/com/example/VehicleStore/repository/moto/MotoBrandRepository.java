package com.example.VehicleStore.repository.moto;

import com.example.VehicleStore.entity.items.moto.MotoBrand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoBrandRepository extends JpaRepository<MotoBrand, Long> {

    List<MotoBrand> findAll(Specification<MotoBrand> spec, Pageable pageable);

}

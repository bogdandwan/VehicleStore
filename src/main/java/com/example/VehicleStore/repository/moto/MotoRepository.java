package com.example.VehicleStore.repository.moto;

import com.example.VehicleStore.entity.items.moto.Motorcycle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Motorcycle, Long> {


    List<Motorcycle> findAll(Specification<Motorcycle> spec, Pageable pageable);

}

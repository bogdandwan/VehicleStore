package com.example.VehicleStore.repository.engine;

import com.example.VehicleStore.entity.items.engine.Engine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineRepository extends JpaRepository<Engine, Long> {

    List<Engine> findAll (Specification<Engine> spec, Pageable pageable);

}

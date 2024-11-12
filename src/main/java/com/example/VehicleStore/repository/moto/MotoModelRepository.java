package com.example.VehicleStore.repository.moto;

import com.example.VehicleStore.entity.items.moto.MotoModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoModelRepository extends JpaRepository<MotoModel, Long> {

    List<MotoModel> findAll(Specification<MotoModel> spec, Pageable pageable);

}

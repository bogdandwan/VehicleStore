package com.example.VehicleStore.repository.rantal;

import com.example.VehicleStore.entity.rental.Rental;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findAll(Specification<Rental> spec, Pageable pageable);

}

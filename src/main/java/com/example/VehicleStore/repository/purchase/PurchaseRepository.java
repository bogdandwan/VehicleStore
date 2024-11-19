package com.example.VehicleStore.repository.purchase;

import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.search.purchase.spec.PurchaseSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAll(Specification<Purchase> spec, Pageable pageable);


}

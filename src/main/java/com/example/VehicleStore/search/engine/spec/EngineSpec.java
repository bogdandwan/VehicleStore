package com.example.VehicleStore.search.engine.spec;

import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.engine.enums.EmissionStandard;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import com.example.VehicleStore.search.engine.EngineSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class EngineSpec implements Specification<Engine> {


    final EngineSearch search;


    @Override
    public Predicate toPredicate(Root<Engine> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (search.getFuel() != null){
            Expression<Collection<Fuel>> typeOfFuel = root.get("fuel");
        }
        if (search.getDisplacement() != null){
            predicates.add(criteriaBuilder.equal(root.get("displacement"), search.getDisplacement()));
        }
        if (search.getDisplacementTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("displacement"), search.getDisplacementTo()));
        }
        if (search.getDisplacementFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("displacement"), search.getDisplacementFrom()));
        }
        if (search.getKwPower() != null){
            predicates.add(criteriaBuilder.equal(root.get("kwPower"), search.getKwPower()));
        }
        if (search.getKwPowerTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("kwPower"), search.getKwPowerTo()));
        }
        if (search.getKwPowerFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("kwPower"), search.getKwPowerFrom()));
        }
        if (search.getHorsePower() != null){
            predicates.add(criteriaBuilder.equal(root.get("horsePower"), search.getHorsePower()));
        }
        if (search.getHorsePowerTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("horsePower"), search.getHorsePowerTo()));
        }
        if (search.getHorsePowerFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("horsePower"), search.getHorsePowerFrom()));
        }
        if (search.getEmissionStandard() != null){
            Expression<Collection<EmissionStandard>> standards = root.get("emissionStandard");
        }
        if (search.getNumberOfCylinders() != null){
            predicates.add(criteriaBuilder.equal(root.get("numberOfCylinders"), search.getNumberOfCylinders()));
        }
        if (search.getFuelConsumption() != null){
            predicates.add(criteriaBuilder.equal(root.get("fuelConsumption"), search.getFuelConsumption()));
        }
        if (search.getFuelConsumptionTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("fuelConsumption"), search.getFuelConsumptionTo()));
        }
        if (search.getFuelConsumptionFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("fuelConsumption"), search.getFuelConsumptionFrom()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

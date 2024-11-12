package com.example.VehicleStore.search.moto.spec;

import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.car.enums.*;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.search.moto.MotoSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MotoSpec implements Specification<Motorcycle> {


    final MotoSearch search;


    @Override
    public Predicate toPredicate(Root<Motorcycle> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


        List<Predicate> predicates = new ArrayList<>();


        if (search.getMotoBrand() != null){
            predicates.add(criteriaBuilder.equal(root.get("motoBrand"),search.getMotoBrand()));
        }
        if (search.getMotoModel() != null){
            predicates.add(criteriaBuilder.equal(root.get("motoModel"),search.getMotoModel()));
        }
        if (search.getEngine() != null){
            predicates.add(criteriaBuilder.equal(root.get("engine"), search.getEngine()));
        }
        if (search.getCondition() != null){
            Expression<Collection<Condition>> condition = root.get("condition");
        }
        if (search.getFuel() != null) {
            Join<Motorcycle, Engine> engineJoin = root.join("engine");
            predicates.add(criteriaBuilder.equal(engineJoin.get("fuel"), search.getFuel()));
        }
        if (search.getPurchasePrice() != null){
            predicates.add(criteriaBuilder.equal(root.get("purchasePrice"), search.getPurchasePrice()));
        }
        if(search.getPurchasePriceTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("purchasePrice"), search.getPurchasePriceTo()));
        }
        if(search.getPurchasePriceFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("purchasePrice"), search.getPurchasePriceFrom()));
        }
        if (search.getRentalPrice() != null){
            predicates.add(criteriaBuilder.equal(root.get("rentalPrice"), search.getRentalPrice()));
        }
        if (search.getRentalPriceTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("rentalPrice"), search.getRentalPriceTo()));
        }
        if (search.getRentalPriceFrom() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("rentalPrice"), search.getRentalPriceFrom()));
        }
        if (search.getYear() != null){
            predicates.add(criteriaBuilder.equal(root.get("year"), search.getYear()));
        }
        if (search.getYearTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("year"), search.getYearTo()));
        }
        if (search.getYearFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("year"), search.getYearFrom()));
        }
        if (search.getMileage() != null){
            predicates.add(criteriaBuilder.equal(root.get("mileage"), search.getMileage()));
        }
        if (search.getMileageTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("mileage"), search.getMileageTo()));
        }
        if (search.getMileageFrom() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("mileage"), search.getMileageFrom()));
        }
        if (search.getTransmissionType() != null){
            Expression<Collection<Transmission>> transmissionType = root.get("transmissionType");
        }
        if (search.getDrivetrain() != null){
            Expression<Collection<Drivetrain>> drivetrain = root.get("drivetrain");
        }
        if (search.getTypeBodyWork() != null){
            Expression<Collection<CarBodyWork>> typeBodyWork = root.get("typeBodyWork");
        }
        if (search.getNubmerOfSeats() != null){
            Expression<Collection<Seats>> numberOfSeats = root.get("numberOfSeats");
        }
        if (search.getAvailable() != null){
            predicates.add(criteriaBuilder.equal(root.get("available"), search.getAvailable()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }
}

package com.example.VehicleStore.search.car.spec;

import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.items.car.enums.*;
import com.example.VehicleStore.search.car.CarSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CarSpec implements Specification<Car> {

    final CarSearch search;

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<>();

        if (search.getCarBrand() != null){
            predicates.add(criteriaBuilder.equal(root.get("carBrand"),search.getCarBrand()));
        }
        if (search.getCarModel() != null){
            predicates.add(criteriaBuilder.equal(root.get("carModel"),search.getCarModel()));
        }
        if (search.getEngine() != null){
            predicates.add(criteriaBuilder.equal(root.get("engine"), search.getEngine()));
        }
        if (search.getCarCondition() != null){
            Expression<Collection<Condition>> carConditions = root.get("carCondition");
        }
        if (search.getFuel() != null) {
            Join<Car, Engine> engineJoin = root.join("engine");
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
        if (search.getCarBodyWork() != null){
            Expression<Collection<CarBodyWork>> carBodyWorks = root.get("carBodyWork");
        }
        if (search.getNumberOfSeats() != null){
            Expression<Collection<Seats>> numberOfSeats = root.get("numberOfSeats");
        }
        if (search.getNumberOfDoors() != null){
            Expression<Collection<Doors>> numberOfDoors = root.get("numberOfDoors");
        }
        if (search.getSteeringWheelSide() != null){
            Expression<Collection<Steering>> steeringWheelSide = root.get("steeringWheelSide");
        }
        if (search.getAvailable() != null){
            predicates.add(criteriaBuilder.equal(root.get("available"), search.getAvailable()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

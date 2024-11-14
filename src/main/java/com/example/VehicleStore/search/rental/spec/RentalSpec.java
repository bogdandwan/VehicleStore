package com.example.VehicleStore.search.rental.spec;

import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.rental.Rental;
import com.example.VehicleStore.entity.rental.enums.RentalStatus;
import com.example.VehicleStore.search.rental.RentalSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class RentalSpec implements Specification<Rental> {

    final RentalSearch search;


    @Override
    public Predicate toPredicate(Root<Rental> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();


        if (search.getClient() != null){
            predicates.add(criteriaBuilder.equal(root.get("client"), search.getClient()));
        }
        if (search.getCar() != null){
            predicates.add(criteriaBuilder.equal(root.get("car"), search.getCar()));
        }
        if (search.getCarBrand() != null){
            predicates.add(criteriaBuilder.equal(root.get("carBrand"), search.getCarBrand()));
        }
        if (search.getMotorcycle() != null){
            predicates.add(criteriaBuilder.equal(root.get("motorcycle"), search.getMotorcycle()));
        }
        if (search.getMotoBrand() != null){
            predicates.add(criteriaBuilder.equal(root.get("motoBrand"), search.getMotoBrand()));
        }
        if (search.getStartDate() != null){
            predicates.add(criteriaBuilder.equal(root.get("startDate"), search.getStartDate()));
        }
        if (search.getStartDateTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("startDate"), search.getStartDateTo()));
        }
        if (search.getStartDateFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("startDate"), search.getStartDateFrom()));
        }
        if (search.getReturnDate() != null){
            predicates.add(criteriaBuilder.equal(root.get("returnDate"), search.getReturnDate()));
        }
        if (search.getRentalPriceTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("returnDate"), search.getReturnDateTo()));
        }
        if (search.getReturnDateFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("returnDate"), search.getReturnDateFrom()));
        }
        if (search.getExpirationDate() != null){
            predicates.add(criteriaBuilder.equal(root.get("expirationDate"), search.getExpirationDate()));
        }
        if (search.getExpirationDateTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("expirationDate"), search.getExpirationDateTo()));
        }
        if (search.getExpirationDateFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("expirationDate"), search.getExpirationDateFrom()));
        }
        if (search.getRentalPrice() != null){
            predicates.add(criteriaBuilder.equal(root.get("rentalPrice"), search.getRentalPrice()));
        }
        if (search.getRentalPriceTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("rentalPrice"), search.getRentalPriceTo()));
        }
        if (search.getRentalPriceFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("rentalPrice"), search.getRentalPriceFrom()));
        }
        if (search.getRentalStatus() != null){
            Expression<Collection<RentalStatus>> rentalStatus = root.get("rentalStatus");
        }
        if (search.getCarYear() != null) {
            Join<Rental, Car> carJoin = root.join("car", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(carJoin.get("year"), search.getCarYear()));
        }
        if (search.getMotoYear() != null){
            Join<Rental, Motorcycle> motoJoin = root.join("motorcycle", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(motoJoin.get("year"), search.getMotoYear()));
        }




        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

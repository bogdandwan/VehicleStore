package com.example.VehicleStore.search.car.spec;

import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.car.CarModelSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CarModelSpec implements Specification<CarModel> {

    final CarModelSearch search;


    @Override
    public Predicate toPredicate(Root<CarModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();


        if (search.getName() != null){
            predicates.add(criteriaBuilder.like(root.get("%name%"), "%"+search.getName()+"%"));
        }
        if (search.getCarBrand() != null){
            predicates.add(criteriaBuilder.equal(root.get("carBrand"),search.getCarBrand()));
        }
        if (search.getAvailable() != null){
            predicates.add(criteriaBuilder.equal(root.get("available"), search.getAvailable()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }
}

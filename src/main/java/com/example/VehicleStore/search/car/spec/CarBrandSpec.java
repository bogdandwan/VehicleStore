package com.example.VehicleStore.search.car.spec;

import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.car.CarBrandSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CarBrandSpec implements Specification<CarBrand> {

    final CarBrandSearch search;


    @Override
    public Predicate toPredicate(Root<CarBrand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (search.getName() != null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+search.getName()+"%"));
        }
        if (search.getBrandCountry() != null){
            predicates.add(criteriaBuilder.like(root.get("brandCountry"), "%"+search.getBrandCountry()+"%"));
        }
        if (search.getAvailable() != null){
            predicates.add(criteriaBuilder.equal(root.get("available"), search.getAvailable()));
        }
        if (search.getCarModel() != null) {
            Join<CarBrand, CarModel> carBrandCarModelJoin = root.join("carModels");
            predicates.add(criteriaBuilder.equal(carBrandCarModelJoin.get("id"), search.getCarModel().getId()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

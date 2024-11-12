package com.example.VehicleStore.search.moto.spec;

import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.search.moto.MotoBrandSearch;
import com.example.VehicleStore.search.moto.MotoSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MotoBrandSpec implements Specification<MotoBrand> {


    final MotoBrandSearch search;


    @Override
    public Predicate toPredicate(Root<MotoBrand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

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
        if (search.getMotoModel() != null) {
            Join<MotoBrand, MotoModel> motoBrandMotoModelJoin = root.join("motoModels");
            predicates.add(criteriaBuilder.equal(motoBrandMotoModelJoin.get("id"), search.getMotoModel().getId()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

package com.example.VehicleStore.search.car.spec;

import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.search.moto.MotoModelSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MotoModelSpec implements Specification<MotoModel> {

    final MotoModelSearch search;


    @Override
    public Predicate toPredicate(Root<MotoModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();


        if (search.getName() != null){
            predicates.add(criteriaBuilder.like(root.get("%name%"), "%"+search.getName()+"%"));
        }
        if (search.getMotoBrand() != null){
            predicates.add(criteriaBuilder.equal(root.get("motoBrand"),search.getMotoBrand()));
        }
        if (search.getAvailable() != null){
            predicates.add(criteriaBuilder.equal(root.get("available"), search.getAvailable()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

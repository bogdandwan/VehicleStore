package com.example.VehicleStore.search.user.spec;

import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.enums.Privilege;
import com.example.VehicleStore.search.user.RoleSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class RoleSpec implements Specification<Role>{

    final RoleSearch search;

    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {


        final List<Predicate> predicates = new ArrayList<>();

        if (search.getName() != null){
            predicates.add(criteriaBuilder.like(root.get("name"),"%"+search.getName()+"%"));
        }
        if (search.getPrivilege() != null){
            Expression<Collection<Privilege>> privileges = root.get("privileges");
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

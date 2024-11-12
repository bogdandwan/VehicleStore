package com.example.VehicleStore.search.user.spec;

import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.search.user.UserSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpec implements Specification<User> {

    private final UserSearch search;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<>();

        if (search.getFirstNameLike() != null){
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%"+search.getFirstNameLike()+"%"));
        }
        if (search.getLastNameLike() != null){
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%"+search.getLastNameLike()+"%"));
        }
        if (search.getEmail() != null){
            predicates.add(criteriaBuilder.like(root.get("email"), "%"+search.getEmail()+"%"));
        }
        if (search.getRole() != null){
            predicates.add(criteriaBuilder.equal(root.get("role"),search.getRole()));
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

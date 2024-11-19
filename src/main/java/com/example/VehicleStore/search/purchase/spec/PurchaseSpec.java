package com.example.VehicleStore.search.purchase.spec;

import com.example.VehicleStore.entity.enums.PaymentMethod;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.entity.purchase.enums.PurchaseStatus;
import com.example.VehicleStore.entity.purchase.enums.PurchaseType;
import com.example.VehicleStore.search.purchase.PurchaseSearch;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PurchaseSpec implements Specification<Purchase> {

    final PurchaseSearch search;


    @Override
    public Predicate toPredicate(Root<Purchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (search.getClient() != null){
            predicates.add(criteriaBuilder.equal(root.get("client"), search.getClient()));
        }
        if (search.getSeller() != null){
            predicates.add(criteriaBuilder.equal(root.get("seller"), search.getSeller()));
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
        if (search.getPurchasePrice() != null){
            predicates.add(criteriaBuilder.equal(root.get("purchasePrice"), search.getPurchasePrice()));
        }
        if (search.getPurchasePriceTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("purchasePrice"), search.getPurchasePrice()));
        }
        if (search.getPurchasePriceFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("purchasePrice"), search.getPurchasePrice()));
        }
        if (search.getPurchaseDate() != null){
            predicates.add(criteriaBuilder.equal(root.get("purchaseDate"), search.getPurchaseDate()));
        }
        if (search.getPurchaseDateTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("purchaseDate"), search.getPurchaseDate()));
        }
        if (search.getPurchaseDateFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("purchaseDate"), search.getPurchaseDate()));
        }
        if (search.getStatus() != null){
            Expression<Collection<PurchaseStatus>> expression = root.get("status");
        }
        if (search.getPaymentMethod() != null){
            Expression<Collection<PaymentMethod>> expression = root.get("paymentMethod");
        }
        if (search.getTransactionId() != null){
            predicates.add(criteriaBuilder.like(root.get("%transactionId%"), "%"+search.getTransactionId()+"%"));
        }
        if (search.getDiscount() != null){
            predicates.add(criteriaBuilder.equal(root.get("discount"), search.getDiscount()));
        }
        if (search.getDiscountTo() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("discount"), search.getDiscountTo()));
        }
        if (search.getDiscountFrom() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("discount"), search.getDiscountFrom()));
        }
        if (search.getPurchaseType() != null){
            Expression<Collection<PurchaseType>> expression = root.get("purchaseType");
        }
        if (search.getLocation() != null){
            predicates.add(criteriaBuilder.like(root.get("%location%"), "%"+search.getLocation()+"%"));
        }
        if (search.getCarYear() != null) {
            Join<Purchase, Car> carJoin = root.join("car", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(carJoin.get("year"), search.getCarYear()));
        }
        if (search.getMotoYear() != null){
            Join<Purchase, Motorcycle> motoJoin = root.join("motorcycle", JoinType.LEFT);
            predicates.add(criteriaBuilder.equal(motoJoin.get("year"), search.getMotoYear()));
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

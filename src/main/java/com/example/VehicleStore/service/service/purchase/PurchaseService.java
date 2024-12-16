package com.example.VehicleStore.service.service.purchase;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.search.purchase.PurchaseSearch;
import com.example.VehicleStore.search.sort.purchase.PurchaseSort;

import java.util.List;

public interface PurchaseService {

    Purchase findById(Long id);

    void save(Purchase purchase);

    List<Purchase> findAll(PurchaseSearch search, Pagination pagination, PurchaseSort sort);

    void softDelete(Long id);

    List<Purchase> findAll();
}

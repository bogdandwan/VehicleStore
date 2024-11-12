package com.example.VehicleStore.service.service.moto;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.search.sort.moto.MotoSort;
import com.example.VehicleStore.search.moto.MotoSearch;

import java.util.List;

public interface MotoService {

    void saveMoto(Motorcycle motorcycle);

    Motorcycle findById(Long id);

    List<Motorcycle> findAll(MotoSearch search, Pagination pagination, MotoSort sort);

    void softDelete(Long id);
}

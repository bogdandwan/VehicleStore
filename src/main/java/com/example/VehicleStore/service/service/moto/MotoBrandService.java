package com.example.VehicleStore.service.service.moto;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.search.moto.MotoBrandSearch;
import com.example.VehicleStore.search.sort.moto.MotoBrandSort;

import java.util.List;

public interface MotoBrandService {

    MotoBrand findById(Long id);

    void save(MotoBrand motoBrand);

    List<MotoBrand> findAll(MotoBrandSearch search, Pagination pagination, MotoBrandSort sort);

    void softDelete(Long id);
}

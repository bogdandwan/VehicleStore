package com.example.VehicleStore.service.service.car;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.search.sort.car.CarBrandSort;
import com.example.VehicleStore.search.car.CarBrandSearch;

import java.util.List;

public interface CarBrandService {

    CarBrand findById(Long id);

    void save(CarBrand carBrand);

    List<CarBrand> findAll(CarBrandSearch search, Pagination pagination, CarBrandSort sort);

    void softDelete(Long id);
}

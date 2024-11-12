package com.example.VehicleStore.service.service.car;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.sort.car.CarModelSort;
import com.example.VehicleStore.search.car.CarModelSearch;

import java.util.List;

public interface CarModelService {

    CarModel findById(Long id);

    void save(CarModel carModel);

    void softDelete(Long id);

    List<CarModel> findAll(CarModelSearch search, Pagination pagination, CarModelSort sort);
}

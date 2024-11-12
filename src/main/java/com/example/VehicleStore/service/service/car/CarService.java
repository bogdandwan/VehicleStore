package com.example.VehicleStore.service.service.car;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.search.sort.car.CarSort;
import com.example.VehicleStore.search.car.CarSearch;

import java.util.List;

public interface CarService {

    void save(Car car);

    Car findById(Long id);

    List<Car> findAll(CarSearch search, Pagination pagination, CarSort sort);

    void softDelete(Long id);
}

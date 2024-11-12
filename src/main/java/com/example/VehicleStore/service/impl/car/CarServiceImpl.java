package com.example.VehicleStore.service.impl.car;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.search.sort.car.CarSort;
import com.example.VehicleStore.repository.car.CarRepository;
import com.example.VehicleStore.search.car.CarSearch;
import com.example.VehicleStore.search.car.spec.CarSpec;
import com.example.VehicleStore.service.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    final CarRepository carRepository;

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> findAll(CarSearch search, Pagination pagination, CarSort sort) {
        return carRepository.findAll(new CarSpec(search), pagination.pageable(buildSort(sort)));
    }

    @Override
    public void softDelete(Long id) {
        final Car car = carRepository.findById(id).orElse(null);
        if (car != null){
            car.setDeletionTime(LocalDateTime.now());
            carRepository.save(car);
        }
    }

    private Sort buildSort(CarSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case PRICE_ASC, PRICE_DESC -> "purchasePrice";
            case RENTAL_PRICE_ASC, RENTAL_PRICE_DESC -> "rentalPrice";
            case YEAR_ASC, YEAR_DESC -> "year";
            case MILEAGE_ASC, MILEAGE_DESC -> "mileage";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }
}

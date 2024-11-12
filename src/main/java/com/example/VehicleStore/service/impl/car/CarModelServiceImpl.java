package com.example.VehicleStore.service.impl.car;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.sort.car.CarModelSort;
import com.example.VehicleStore.repository.car.CarModelRepository;
import com.example.VehicleStore.search.car.CarModelSearch;
import com.example.VehicleStore.search.car.spec.CarModelSpec;
import com.example.VehicleStore.service.service.car.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    final CarModelRepository carModelRepository;

    @Override
    public CarModel findById(Long id) {
        return carModelRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CarModel carModel) {
        carModelRepository.save(carModel);
    }

    @Override
    public List<CarModel> findAll(CarModelSearch search, Pagination pagination, CarModelSort sort) {
        return carModelRepository.findAll(new CarModelSpec(search), pagination.pageable(buildSort(sort)));
    }


    private Sort buildSort(CarModelSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case NAME_ASC, NAME_DESC -> "name";
            case BRAND_NAME_ASC, BRAND_NAME_DESC -> "carBrand.name";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }


    @Override
    public void softDelete(Long id) {
        final CarModel carModel = carModelRepository.findById(id).orElse(null);
        if (carModel != null){
            carModel.setDeletionTime(LocalDateTime.now());
            carModelRepository.save(carModel);
        }
    }

}

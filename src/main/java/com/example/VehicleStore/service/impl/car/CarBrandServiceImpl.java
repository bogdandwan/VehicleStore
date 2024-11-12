package com.example.VehicleStore.service.impl.car;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.search.sort.car.CarBrandSort;
import com.example.VehicleStore.repository.car.CarBrandRepository;
import com.example.VehicleStore.search.car.CarBrandSearch;
import com.example.VehicleStore.search.car.spec.CarBrandSpec;
import com.example.VehicleStore.service.service.car.CarBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    final CarBrandRepository carBrandRepository;


    @Override
    public CarBrand findById(Long id) {
        return carBrandRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CarBrand carBrand) {
        carBrandRepository.save(carBrand);
    }

    @Override
    public List<CarBrand> findAll(CarBrandSearch search, Pagination pagination, CarBrandSort sort) {
        return carBrandRepository.findAll(new CarBrandSpec(search), pagination.pageable(buildSort(sort)));
    }

    private Sort buildSort(CarBrandSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case NAME_ASC, NAME_DESC -> "name";
            case BRAND_COUNTRY_ASC, BRAND_COUNTRY_DESC -> "brandCountry";
            case AVAILABLE_ASC, AVAILABLE_DESC -> "available";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }

    @Override
    public void softDelete(Long id) {
        final CarBrand carBrand = carBrandRepository.findById(id).orElse(null);
        if (carBrand != null){
            carBrand.setDeletionTime(LocalDateTime.now());
            carBrandRepository.save(carBrand);
        }
    }
}

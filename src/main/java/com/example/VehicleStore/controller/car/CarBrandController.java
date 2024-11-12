package com.example.VehicleStore.controller.car;

import com.example.VehicleStore.dto.car.ApiCarBrand;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.sort.car.CarBrandSort;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.exceptions.ValidationException;
import com.example.VehicleStore.search.car.CarBrandSearch;
import com.example.VehicleStore.service.service.car.CarBrandService;
import com.example.VehicleStore.service.service.car.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CarBrandController {

    final CarBrandService carBrandService;
    final CarModelService carModelService;



    @PostMapping("/car-brand")
    public void saveOrUpdateCarBrand(@RequestBody ApiCarBrand apiCarBrand) {
        final CarBrand carBrand;

        if (apiCarBrand.getId() != null) {
            carBrand = carBrandService.findById(apiCarBrand.getId());
            if (carBrand == null) {
                throw new NotFoundException("Car Brand not found");
            }
        } else {
            carBrand = new CarBrand();
        }

        carBrand.setName(apiCarBrand.getName())
                .setBrandCountry(apiCarBrand.getBrandCountry())
                .setAvailable(apiCarBrand.getAvailable());

        if (apiCarBrand.getCarModels() != null && !apiCarBrand.getCarModels().isEmpty()) {
            List<CarModel> carModels = apiCarBrand.getCarModels().stream().map(apiCarModel -> {
                CarModel carModel = carModelService.findById(apiCarModel.getId());
                if (carModel == null) {
                    throw new NotFoundException("Car Model not found.");
                }
                return carModel;
            }).collect(Collectors.toList());
            carBrand.setCarModels(carModels);
        } else {
            throw new ValidationException("Car Brand must contain at least one Car Model");
        }

        carBrandService.save(carBrand);
    }


    @GetMapping("/car-brand/{id}")
    public ApiCarBrand getById(@PathVariable Long id){
        final CarBrand carBrand = carBrandService.findById(id);
        if (carBrand == null){
            throw new NotFoundException("Car Brand not found");
        }
        return new ApiCarBrand(carBrand);
    }



    @GetMapping("/car-brands")
    public List<ApiCarBrand> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brandCountry,
            @RequestParam(required = false) Long carModelId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) CarBrandSort sort,
            Pagination pagination){

        final CarBrandSearch search = new CarBrandSearch()
                .setName(name)
                .setBrandCountry(brandCountry)
                .setAvailable(available);

        if (carModelId != null){
            final CarModel carModel = carModelService.findById(carModelId);
            if (carModel == null){
                throw new NotFoundException("Car Model not found.");
            }
            search.setCarModel(carModel);
        }

        List<CarBrand> carBrands = carBrandService.findAll(search, pagination, sort);

        return carBrands.stream().map(ApiCarBrand::new).collect(Collectors.toList());
    }



    @DeleteMapping("/car-brand/{id}")
    public void deleteCarBrand(@PathVariable Long id){
        carBrandService.softDelete(id);
    }

}

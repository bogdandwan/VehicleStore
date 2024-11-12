package com.example.VehicleStore.controller.car;

import com.example.VehicleStore.dto.car.ApiCarModel;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.search.sort.car.CarModelSort;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.exceptions.ValidationException;
import com.example.VehicleStore.search.car.CarModelSearch;
import com.example.VehicleStore.service.service.car.CarBrandService;
import com.example.VehicleStore.service.service.car.CarModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CarModelController {

    final CarBrandService carBrandService;
    final CarModelService carModelService;


    @PostMapping("/car-model")
    public void saveOrUpdateCarModel(@RequestBody @Valid ApiCarModel apiCarModel) {
        final CarModel carModel;

        if (apiCarModel.getId() == null) {
            carModel = new CarModel();
        } else {
            carModel = carModelService.findById(apiCarModel.getId());
            if (carModel == null) {
                throw new NotFoundException("Car Model not found.");
            }
        }
        if (apiCarModel.getName() != null && !apiCarModel.getName().isEmpty()) {
            carModel.setName(apiCarModel.getName());
        } else if (carModel.getId() == null) {
            throw new ValidationException("Car Model name is required for new entries.");
        }

        if (apiCarModel.getCarBrand() != null && apiCarModel.getCarBrand().getId() != null) {
            CarBrand carBrand = carBrandService.findById(apiCarModel.getCarBrand().getId());
            if (carBrand == null) {
                throw new NotFoundException("Car Brand not found");
            }
            carModel.setCarBrand(carBrand);
        } else {
            throw new ValidationException("Car Brand is required");
        }

        carModelService.save(carModel);
    }


    @GetMapping("car-model/{id}")
    public ApiCarModel getCarModelById(@PathVariable Long id){
        final CarModel carModel = carModelService.findById(id);
        if (carModel == null) {
            throw new NotFoundException("Car Model not found");
        }
        return new ApiCarModel(carModel);
    }


    public List<ApiCarModel> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long carBrandId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) CarModelSort sort,
            Pagination pagination){

        final CarModelSearch search = new CarModelSearch()
                .setName(name)
                .setAvailable(available);
        if(carBrandId != null){
            final CarBrand carBrand = carBrandService.findById(carBrandId);
            if (carBrand == null){
                throw new NotFoundException("Car Brand not found.");
            }
            search.setCarBrand(carBrand);
        }

        final List<CarModel> carModels = carModelService.findAll(search, pagination, sort);


        return carModels.stream().map(ApiCarModel::new).collect(Collectors.toList());
    }



    @DeleteMapping("/car-model/{id}")
    public void deleteCarModel(@PathVariable Long id){
        carModelService.softDelete(id);
    }

}

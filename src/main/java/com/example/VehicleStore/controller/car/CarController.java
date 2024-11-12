package com.example.VehicleStore.controller.car;

import com.example.VehicleStore.dto.car.ApiCar;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.car.CarModel;
import com.example.VehicleStore.entity.items.car.enums.*;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.car.CarSearch;
import com.example.VehicleStore.search.sort.car.CarSort;
import com.example.VehicleStore.service.service.car.CarBrandService;
import com.example.VehicleStore.service.service.car.CarModelService;
import com.example.VehicleStore.service.service.car.CarService;
import com.example.VehicleStore.service.service.engine.EngineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CarController {

    final CarService carService;
    final CarBrandService carBrandService;
    final CarModelService carModelService;
    final EngineService engineService;

    @PostMapping("/car")
    public void saveOrUpdateCar(@RequestBody @Valid ApiCar apiCar) {
        final Car car;

        if (apiCar.getId() == null) {
            car = new Car();
        } else {
            car = carService.findById(apiCar.getId());
            if (car == null) {
                throw new NotFoundException("Car not found");
            }
        }

        if (apiCar.getCarBrand() != null) {
            car.setCarBrand(carBrandService.findById(apiCar.getCarBrand().getId()));
        }
        if (apiCar.getCarModel() != null) {
            car.setCarModel(carModelService.findById(apiCar.getCarModel().getId()));
        }
        if (apiCar.getEngine() != null) {
            car.setEngine(engineService.findById(apiCar.getEngine().getId()));
        }
        if (apiCar.getCondition() != null) {
            car.setCondition(apiCar.getCondition());
        }
        if (apiCar.getPurchasePrice() != null) {
            car.setPurchasePrice(apiCar.getPurchasePrice());
        }
        if (apiCar.getRentalPrice() != null) {
            car.setRentalPrice(apiCar.getRentalPrice());
        }
        if (apiCar.getYear() != null) {
            car.setYear(apiCar.getYear());
        }
        if (apiCar.getMileage() != null) {
            car.setMileage(apiCar.getMileage());
        }
        if (apiCar.getColor() != null) {
            car.setColor(apiCar.getColor());
        }
        if (apiCar.getTransmissionType() != null) {
            car.setTransmissionType(apiCar.getTransmissionType());
        }
        if (apiCar.getDrivetrain() != null) {
            car.setDrivetrain(apiCar.getDrivetrain());
        }
        if (apiCar.getCarBodyWork() != null) {
            car.setCarBodyWork(apiCar.getCarBodyWork());
        }
        if (apiCar.getNumberOfSeats() != null) {
            car.setNumberOfSeats(apiCar.getNumberOfSeats());
        }
        if (apiCar.getNumberOfDoors() != null) {
            car.setNumberOfDoors(apiCar.getNumberOfDoors());
        }
        if (apiCar.getSteeringWheelSide() != null) {
            car.setSteeringWheelSide(apiCar.getSteeringWheelSide());
        }
        if (apiCar.getAvailable() != null) {
            car.setAvailable(apiCar.getAvailable());
        }

        carService.save(car);
    }



    @GetMapping("/car/{id}")
    public ApiCar findById(@PathVariable Long id){
        final Car car = carService.findById(id);
        if (car == null){
            throw new NotFoundException("Car not found");
        }
        return new ApiCar(car);
    }



    @GetMapping("/cars")
    public List<ApiCar> getAll(
            @RequestParam(required = false) Long carBrandId,
            @RequestParam(required = false) Long carModelId,
            @RequestParam(required = false) Long engineId,
            @RequestParam(required = false) Fuel fuel,
            @RequestParam(required = false) Condition carCondition,
            @RequestParam(required = false) Integer purchasePrice,
            @RequestParam(required = false) Integer purchasePriceFrom,
            @RequestParam(required = false) Integer purchasePriceTo,
            @RequestParam(required = false) Integer rentalPrice,
            @RequestParam(required = false) Integer rentalPriceFrom,
            @RequestParam(required = false) Integer rentalPriceTo,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(required = false) Double mileage,
            @RequestParam(required = false) Double mileageFrom,
            @RequestParam(required = false) Double mileageTo,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Transmission transmissionType,
            @RequestParam(required = false) Drivetrain drivetrain,
            @RequestParam(required = false) CarBodyWork carBodyWork,
            @RequestParam(required = false) Seats numberOfSeats,
            @RequestParam(required = false) Doors numberOfDoors,
            @RequestParam(required = false) Steering steeringWheelSide,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) CarSort sort,
            Pagination pagination) {

        final CarSearch search = new CarSearch()
                .setCarCondition(carCondition)
                .setFuel(fuel)
                .setPurchasePrice(purchasePrice)
                .setPurchasePriceTo(purchasePriceTo)
                .setPurchasePriceFrom(purchasePriceFrom)
                .setRentalPrice(rentalPrice)
                .setRentalPriceTo(rentalPriceTo)
                .setRentalPriceFrom(rentalPriceFrom)
                .setYear(year)
                .setYearFrom(yearFrom)
                .setYearTo(yearTo)
                .setMileage(mileage)
                .setMileageFrom(mileageFrom)
                .setMileageTo(mileageTo)
                .setColor(color)
                .setTransmissionType(transmissionType)
                .setDrivetrain(drivetrain)
                .setCarBodyWork(carBodyWork)
                .setNumberOfSeats(numberOfSeats)
                .setNumberOfDoors(numberOfDoors)
                .setSteeringWheelSide(steeringWheelSide)
                .setAvailable(available);

        if(carBrandId != null){
            final CarBrand carBrand = carBrandService.findById(carBrandId);
            if (carBrand == null){
                throw new NotFoundException("Car Brand not found.");
            }
            search.setCarBrand(carBrand);
        }
        if (carModelId != null){
            final CarModel carModel = carModelService.findById(carModelId);
            if (carModel == null){
                throw new NotFoundException("Car Model not found.");
            }
            search.setCarModel(carModel);
        }
        if (engineId != null){
            final Engine engine = engineService.findById(engineId);
            if (engine == null){
                throw new NotFoundException("Engine not found");
            }
            search.setEngine(engine);
        }

        final List<Car> cars = carService.findAll(search, pagination, sort);

        return cars.stream().map(ApiCar::new).collect(Collectors.toList());

    }




    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.softDelete(id);
    }

}

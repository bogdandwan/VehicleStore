package com.example.VehicleStore.controller.moto;

import com.example.VehicleStore.dto.moto.ApiMoto;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.enums.Drivetrain;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.items.moto.enums.Seats;
import com.example.VehicleStore.entity.items.moto.enums.Transmission;
import com.example.VehicleStore.entity.items.moto.enums.TypeBodyWork;
import com.example.VehicleStore.search.sort.moto.MotoSort;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.moto.MotoSearch;
import com.example.VehicleStore.service.service.engine.EngineService;
import com.example.VehicleStore.service.service.moto.MotoBrandService;
import com.example.VehicleStore.service.service.moto.MotoModelService;
import com.example.VehicleStore.service.service.moto.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MotoController {

    final MotoService motoService;
    final MotoBrandService motoBrandService;
    final MotoModelService motoModelService;
    final EngineService engineService;



    @PostMapping("/motorcycle")
    private void saveOrUpdateMoto(@RequestBody ApiMoto apiMoto) {
        Motorcycle motorcycle;

        if (apiMoto.getId() != null) {
            motorcycle = motoService.findById(apiMoto.getId());
            if (motorcycle == null) {
                throw new NotFoundException("Motorcycle not found");
            }
        } else {
            motorcycle = new Motorcycle();
        }

        motorcycle.setCondition(apiMoto.getCondition())
                .setPurchasePrice(apiMoto.getPurchasePrice())
                .setRentalPrice(apiMoto.getRentalPrice())
                .setYear(apiMoto.getYear())
                .setMileage(apiMoto.getMileage())
                .setColor(apiMoto.getColor())
                .setTransmissionType(apiMoto.getTransmissionType())
                .setTypeBodyWork(apiMoto.getTypeBodyWork())
                .setSeats(apiMoto.getSeats())
                .setAvailable(apiMoto.getAvailable());

        if (apiMoto.getMotoBrand() != null) {
            final MotoBrand motoBrand = motoBrandService.findById(apiMoto.getMotoBrand().getId());
            if (motoBrand == null) {
                throw new NotFoundException("Brand not found");
            }
            motorcycle.setMotoBrand(motoBrand);
        }
        if (apiMoto.getMotoModel() != null) {
            final MotoModel motoModel = motoModelService.findById(apiMoto.getMotoModel().getId());
            if (motoModel == null) {
                throw new NotFoundException("Model not found.");
            }
            motorcycle.setMotoModel(motoModel);
        }
        if (apiMoto.getEngine() != null) {
            final Engine engine = engineService.findById(apiMoto.getEngine().getId());
            if (engine == null) {
                throw new NotFoundException("Engine not found.");
            }
            motorcycle.setEngine(engine);
        }
        motoService.saveMoto(motorcycle);
    }




    @GetMapping("/motorcycle/{id}")
    public ApiMoto getById(@PathVariable Long id){
        final Motorcycle motorcycle = motoService.findById(id);
        if (motorcycle == null){
            throw new NotFoundException("Motorcycle not found");
        }
        return new ApiMoto(motorcycle);
    }



    @GetMapping("/motorcycles")
    public List<ApiMoto> getAll(
            @RequestParam(required = false) Long motoBrandId,
            @RequestParam(required = false) Long motoModelId,
            @RequestParam(required = false) Long engineId,
            @RequestParam(required = false) Fuel fuel,
            @RequestParam(required = false) Condition condition,
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
            @RequestParam(required = false) TypeBodyWork typeBodyWork,
            @RequestParam(required = false) Seats seats,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) MotoSort sort,
            Pagination pagination){


        final MotoSearch search = new MotoSearch()
                .setCondition(condition)
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
                .setTypeBodyWork(typeBodyWork)
                .setNubmerOfSeats(seats)
                .setAvailable(available);

        if (motoBrandId != null){
            final MotoBrand motoBrand = motoBrandService.findById(motoBrandId);
            if (motoBrand == null){
                throw new NotFoundException("Brand not found");
            }
            search.setMotoBrand(motoBrand);
        }
        if (motoModelId != null){
            final MotoModel motoModel = motoModelService.findById(motoModelId);
            if (motoModel == null){
                throw new NotFoundException("Model not found");
            }
            search.setMotoModel(motoModel);
        }
        if (engineId != null){
            final Engine engine = engineService.findById(engineId);
            if (engine == null){
                throw new NotFoundException("Engine not found");
            }
            search.setEngine(engine);
        }

        final List<Motorcycle> motorcycles = motoService.findAll(search, pagination, sort);

        return motorcycles.stream().map(ApiMoto::new).collect(Collectors.toList());

    }


    @DeleteMapping("motorcycle/{id}")
    public void deleteMoto(@PathVariable Long id){
        motoService.softDelete(id);
    }
}

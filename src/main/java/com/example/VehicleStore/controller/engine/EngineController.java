package com.example.VehicleStore.controller.engine;

import com.example.VehicleStore.dto.engine.ApiEngine;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.items.engine.enums.EmissionStandard;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import com.example.VehicleStore.search.sort.engine.EngineSort;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.engine.EngineSearch;
import com.example.VehicleStore.service.service.engine.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EngineController {

    final EngineService engineService;

    @PostMapping("/engine")
    public void saveOrUpdateEngine(@RequestBody ApiEngine apiEngine) {
        final Engine engine;

        if (apiEngine.getId() == null){
            engine = new Engine();
        }else {
            engine = engineService.findById(apiEngine.getId());
            if (engine == null){
                throw new NotFoundException("Engine not found.");
            }
        }
        if (apiEngine.getEmissionStandard() != null) {
            engine.setEmissionStandard(apiEngine.getEmissionStandard());
        }
        if (apiEngine.getFuel() != null) {
            engine.setFuel(apiEngine.getFuel());
        }
        if (apiEngine.getDisplacement() != null) {
            engine.setDisplacement(apiEngine.getDisplacement());
        }
        if (apiEngine.getKwPower() != null) {
            engine.setKwPower(apiEngine.getKwPower());
        }
        if (apiEngine.getFuelConsumption() != null) {
            engine.setFuelConsumption(apiEngine.getFuelConsumption());
        }
        if (apiEngine.getHorsePower() != null) {
            engine.setHorsePower(apiEngine.getHorsePower());
        }
        if (apiEngine.getNumberOfCylinders() != null) {
            engine.setNumberOfCylinders(apiEngine.getNumberOfCylinders());
        }
        engineService.save(engine);
    }

    @GetMapping("/engine/{id}")
    public ApiEngine getById(@PathVariable Long id){
        final Engine engine = engineService.findById(id);
        if (engine == null){
            throw new NotFoundException("Engine not found");
        }
        return new ApiEngine(engine);
    }


    @GetMapping("/engines")
    public List<ApiEngine> getAll(
            @RequestParam(required = false) Fuel fuel,
            @RequestParam(required = false) Integer displacement,
            @RequestParam(required = false) Integer displacementFrom,
            @RequestParam(required = false) Integer displacementTo,
            @RequestParam(required = false) Double kwPower,
            @RequestParam(required = false) Double kwPowerFrom,
            @RequestParam(required = false) Double kwPowerTo,
            @RequestParam(required = false) Integer horsePower,
            @RequestParam(required = false) Integer horsePowerFrom,
            @RequestParam(required = false) Integer horsePowerTo,
            @RequestParam(required = false) EmissionStandard emissionStandard,
            @RequestParam(required = false) Integer numberOfCylinders,
            @RequestParam(required = false) Double fuelConsumption,
            @RequestParam(required = false) Double fuelConsumptionFrom,
            @RequestParam(required = false) Double fuelConsumptionTo,
            @RequestParam(required = false) EngineSort sort,
            Pagination pagination){

        final EngineSearch search = new EngineSearch()
                .setFuel(fuel)
                .setDisplacement(displacement)
                .setDisplacementFrom(displacementFrom)
                .setDisplacementTo(displacementTo)
                .setKwPower(kwPower)
                .setKwPowerFrom(kwPowerFrom)
                .setKwPowerTo(kwPowerTo)
                .setHorsePower(horsePower)
                .setHorsePowerFrom(horsePowerFrom)
                .setHorsePowerTo(horsePowerTo)
                .setEmissionStandard(emissionStandard)
                .setNumberOfCylinders(numberOfCylinders)
                .setFuelConsumption(fuelConsumption)
                .setFuelConsumptionFrom(fuelConsumptionFrom)
                .setFuelConsumptionTo(fuelConsumptionTo);


        List<Engine> engines = engineService.findAll(search, pagination, sort);

        return engines.stream().map(ApiEngine::new).collect(Collectors.toList());
    }



    @DeleteMapping("/engine/{id}")
    public void deleteEngine(@PathVariable Long id){
        engineService.softDelete(id);
    }

}

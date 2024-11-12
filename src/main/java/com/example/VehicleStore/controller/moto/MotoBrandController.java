package com.example.VehicleStore.controller.moto;


import com.example.VehicleStore.dto.moto.ApiMotoBrand;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.exceptions.ValidationException;
import com.example.VehicleStore.search.moto.MotoBrandSearch;
import com.example.VehicleStore.search.sort.moto.MotoBrandSort;
import com.example.VehicleStore.service.service.moto.MotoBrandService;
import com.example.VehicleStore.service.service.moto.MotoModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MotoBrandController {


    final MotoModelService motoModelService;
    final MotoBrandService motoBrandService;



    @PostMapping("/moto-brand")
    public void saveOrUpdateMotoBrand(@RequestBody ApiMotoBrand apiMotoBrand) {
        MotoBrand motoBrand;

        if (apiMotoBrand.getId() != null) {
            motoBrand = motoBrandService.findById(apiMotoBrand.getId());
            if (motoBrand == null) {
                throw new NotFoundException("MotoBrand not found");
            }
        } else {
            motoBrand = new MotoBrand();
        }

        motoBrand.setName(apiMotoBrand.getName())
                .setBrandCountry(apiMotoBrand.getBrandCountry())
                .setAvailable(apiMotoBrand.getAvailable());

        if (apiMotoBrand.getMotoModels() != null && !apiMotoBrand.getMotoModels().isEmpty()) {
            List<MotoModel> motoModels = apiMotoBrand.getMotoModels().stream().map(apiMotoModel -> {
                MotoModel motoModel = motoModelService.findById(apiMotoModel.getId());
                if (motoModel == null) {
                    throw new NotFoundException("Model not found.");
                }
                return motoModel;
            }).collect(Collectors.toList());
            motoBrand.setMotoModels(motoModels);
        } else {
            throw new ValidationException("Brand can't be empty");
        }

        motoBrandService.save(motoBrand);
    }




    @GetMapping("/moto-brand/{id}")
    public ApiMotoBrand getById(@PathVariable Long id){
        final MotoBrand motoBrand = motoBrandService.findById(id);
        if (motoBrand == null){
            throw new NotFoundException("Brand not found");
        }
        return new ApiMotoBrand(motoBrand);
    }




    @GetMapping("/moto-brands")
    public List<ApiMotoBrand> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brandCountry,
            @RequestParam(required = false) Long motoModelId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) MotoBrandSort sort,
            Pagination pagination){

        final MotoBrandSearch search = new MotoBrandSearch()
                .setName(name)
                .setBrandCountry(brandCountry)
                .setAvailable(available);

        if (motoModelId != null){
            final MotoModel motoModel = motoModelService.findById(motoModelId);
            if (motoModel == null){
                throw new NotFoundException("Model not found");
            }
            search.setMotoModel(motoModel);
        }

        final List<MotoBrand> motoBrands = motoBrandService.findAll(search, pagination, sort);

        return motoBrands.stream().map(ApiMotoBrand::new).collect(Collectors.toList());
    }



    @DeleteMapping("/moto-brand")
    public void deleteMotoBrand(@PathVariable Long id){
        motoBrandService.softDelete(id);
    }

}

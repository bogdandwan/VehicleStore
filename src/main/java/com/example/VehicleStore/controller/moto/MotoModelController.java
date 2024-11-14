package com.example.VehicleStore.controller.moto;

import com.example.VehicleStore.dto.moto.ApiMotoModel;
import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.exceptions.ValidationException;
import com.example.VehicleStore.search.moto.MotoModelSearch;
import com.example.VehicleStore.search.sort.moto.MotoModelSort;
import com.example.VehicleStore.service.service.moto.MotoBrandService;
import com.example.VehicleStore.service.service.moto.MotoModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MotoModelController {

    final MotoModelService motoModelService;
    final MotoBrandService motoBrandService;



    @PostMapping("/moto-model")
    public void saveOrUpdateMotoModel(@RequestBody ApiMotoModel apiMotoModel) {
        MotoModel motoModel;

        if (apiMotoModel.getId() != null) {
            motoModel = motoModelService.findById(apiMotoModel.getId());
            if (motoModel == null) {
                throw new NotFoundException("MotoModel not found");
            }
        } else {
            motoModel = new MotoModel();
        }

        motoModel.setName(apiMotoModel.getName());

        if (apiMotoModel.getMotoBrand() != null) {
            final MotoBrand motoBrand = motoBrandService.findById(apiMotoModel.getMotoBrand().getId());
            if (motoBrand == null) {
                throw new NotFoundException("Brand not found");
            }
            motoModel.setMotoBrand(motoBrand);
        } else {
            throw new ValidationException("Brand is required");
        }

        motoModelService.save(motoModel);
    }




    @GetMapping("moto-model/{id}")
    public ApiMotoModel getById(@PathVariable Long id){
        final MotoModel motoModel = motoModelService.findById(id);
        if (motoModel == null){
            throw new NotFoundException("Model not found");
        }
        return new ApiMotoModel(motoModel);
    }




    @GetMapping("/moto-models")
    public List<ApiMotoModel> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long motoBrandId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) MotoModelSort sort,
            Pagination pagination){

        final MotoModelSearch search = new MotoModelSearch()
                .setName(name)
                .setAvailable(available);

        if (motoBrandId != null){
            final MotoBrand motoBrand = motoBrandService.findById(motoBrandId);
            if (motoBrand == null){
                throw new NotFoundException("Brand not found");
            }
            search.setMotoBrand(motoBrand);
        }

        final List<MotoModel> motoModels = motoModelService.findAll(search, pagination, sort);

        return motoModels.stream().map(ApiMotoModel::new).collect(Collectors.toList());

    }



    @DeleteMapping("/moto-model/{id}")
    public void deleteMotoModel(@PathVariable Long id){
        motoModelService.softDelete(id);
    }


}

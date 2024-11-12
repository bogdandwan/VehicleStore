package com.example.VehicleStore.service.service.moto;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.search.moto.MotoModelSearch;
import com.example.VehicleStore.search.sort.moto.MotoModelSort;

import java.util.List;

public interface MotoModelService {

    MotoModel findById(Long id);

    void save(MotoModel motoModel);

    List<MotoModel> findAll(MotoModelSearch search, Pagination pagination, MotoModelSort sort);

    void softDelete(Long id);
}

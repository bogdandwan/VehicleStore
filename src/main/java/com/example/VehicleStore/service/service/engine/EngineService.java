package com.example.VehicleStore.service.service.engine;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.search.sort.engine.EngineSort;
import com.example.VehicleStore.search.engine.EngineSearch;

import java.util.List;

public interface EngineService {

    Engine findById(Long id);

    void save(Engine engine);

    List<Engine> findAll(EngineSearch search, Pagination pagination, EngineSort sort);

    void softDelete(Long id);
}

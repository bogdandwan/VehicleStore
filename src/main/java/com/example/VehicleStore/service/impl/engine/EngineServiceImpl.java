package com.example.VehicleStore.service.impl.engine;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.search.sort.engine.EngineSort;
import com.example.VehicleStore.repository.engine.EngineRepository;
import com.example.VehicleStore.search.engine.EngineSearch;
import com.example.VehicleStore.search.engine.spec.EngineSpec;
import com.example.VehicleStore.service.service.engine.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineServiceImpl implements EngineService {

    final EngineRepository engineRepository;


    @Override
    public Engine findById(Long id) {
        return engineRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Engine engine) {
        engineRepository.save(engine);
    }

    @Override
    public List<Engine> findAll(EngineSearch search, Pagination pagination, EngineSort sort) {
        return engineRepository.findAll(new EngineSpec(search), pagination.pageable(buildSort(sort)));
    }

    private Sort buildSort(EngineSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id"); // Podrazumevano sortiranje
        }

        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case FUEL_ASC, FUEL_DESC -> "fuel";
            case DISPLACEMENT_ASC, DISPLACEMENT_DESC -> "displacement";
            case KW_POWER_ASC, KW_POWER_DESC -> "kwPower";
            case HORSE_POWER_ASC, HORSE_POWER_DESC -> "horsePower";
            case EMISSION_STANDARD_ASC, EMISSION_STANDARD_DESC -> "emissionStandard";
            case NUMBER_OF_CYLINDERS_ASC, NUMBER_OF_CYLINDERS_DESC -> "numberOfCylinders";
            case FUEL_CONSUMPTION_ASC, FUEL_CONSUMPTION_DESC -> "fuelConsumption";
            default -> "id"; // Default sort
        };

        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }

    @Override
    public void softDelete(Long id) {
        final Engine engine = engineRepository.findById(id).orElse(null);
        if (engine != null){
            engine.setDeletionTime(LocalDateTime.now());
            engineRepository.save(engine);
        }
    }
}

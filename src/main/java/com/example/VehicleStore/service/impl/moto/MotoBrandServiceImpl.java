package com.example.VehicleStore.service.impl.moto;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.repository.moto.MotoBrandRepository;
import com.example.VehicleStore.search.moto.MotoBrandSearch;
import com.example.VehicleStore.search.moto.spec.MotoBrandSpec;
import com.example.VehicleStore.search.sort.moto.MotoBrandSort;
import com.example.VehicleStore.service.service.moto.MotoBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoBrandServiceImpl implements MotoBrandService {

    final MotoBrandRepository motoBrandRepository;


    @Override
    public MotoBrand findById(Long id) {
        return motoBrandRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MotoBrand motoBrand) {
        motoBrandRepository.save(motoBrand);
    }

    private Sort buildSort(MotoBrandSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case NAME_ASC, NAME_DESC -> "name";
            case BRAND_COUNTRY_ASC, BRAND_COUNTRY_DESC -> "brandCountry";
            case AVAILABLE_ASC, AVAILABLE_DESC -> "available";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }

    @Override
    public List<MotoBrand> findAll(MotoBrandSearch search, Pagination pagination, MotoBrandSort sort) {
        return motoBrandRepository.findAll(new MotoBrandSpec(search), pagination.pageable(buildSort(sort)));
    }

    @Override
    public void softDelete(Long id) {
        final MotoBrand motoBrand = motoBrandRepository.findById(id).orElse(null);
        if (motoBrand != null){
            motoBrand.setDeletionTime(LocalDateTime.now());
            motoBrandRepository.save(motoBrand);
        }
    }
}

package com.example.VehicleStore.service.impl.moto;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.search.sort.moto.MotoSort;
import com.example.VehicleStore.repository.moto.MotoRepository;
import com.example.VehicleStore.search.moto.MotoSearch;
import com.example.VehicleStore.search.moto.spec.MotoSpec;
import com.example.VehicleStore.service.service.moto.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoServiceImpl implements MotoService {

    final MotoRepository motoRepository;


    @Override
    public void saveMoto(Motorcycle motorcycle) {
        motoRepository.save(motorcycle);
    }

    @Override
    public Motorcycle findById(Long id) {
        return motoRepository.findById(id).orElse(null);
    }

    private Sort buildSort(MotoSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case PRICE_ASC, PRICE_DESC -> "purchasePrice";
            case RENTAL_PRICE_ASC, RENTAL_PRICE_DESC -> "rentalPrice";
            case YEAR_ASC, YEAR_DESC -> "year";
            case MILEAGE_ASC, MILEAGE_DESC -> "mileage";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }

    @Override
    public List<Motorcycle> findAll(MotoSearch search, Pagination pagination, MotoSort sort) {
        return motoRepository.findAll(new MotoSpec(search), pagination.pageable(buildSort(sort)));
    }

    @Override
    public void softDelete(Long id) {
        final Motorcycle motorcycle = motoRepository.findById(id).orElse(null);
        if (motorcycle != null){
            motorcycle.setDeletionTime(LocalDateTime.now());
            motoRepository.save(motorcycle);
        }
    }


}

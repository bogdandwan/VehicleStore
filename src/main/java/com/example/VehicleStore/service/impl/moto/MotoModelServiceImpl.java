package com.example.VehicleStore.service.impl.moto;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.items.moto.MotoModel;
import com.example.VehicleStore.repository.moto.MotoModelRepository;
import com.example.VehicleStore.search.car.spec.MotoModelSpec;
import com.example.VehicleStore.search.moto.MotoModelSearch;
import com.example.VehicleStore.search.sort.car.CarModelSort;
import com.example.VehicleStore.search.sort.moto.MotoModelSort;
import com.example.VehicleStore.service.service.moto.MotoModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoModelServiceImpl implements MotoModelService {


    final MotoModelRepository motoModelRepository;


    @Override
    public MotoModel findById(Long id) {
        return motoModelRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MotoModel motoModel) {
        motoModelRepository.save(motoModel);
    }

    @Override
    public List<MotoModel> findAll(MotoModelSearch search, Pagination pagination, MotoModelSort sort) {
        return motoModelRepository.findAll(new MotoModelSpec(search), pagination.pageable(buildSort(sort)));
    }

    @Override
    public void softDelete(Long id) {
        final MotoModel motoModel = motoModelRepository.findById(id).orElse(null);
        if (motoModel != null){
            motoModel.setDeletionTime(LocalDateTime.now());
            motoModelRepository.save(motoModel);
        }
    }

    private Sort buildSort(MotoModelSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case NAME_ASC, NAME_DESC -> "name";
            case BRAND_NAME_ASC, BRAND_NAME_DESC -> "motoBrand.name";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }


}

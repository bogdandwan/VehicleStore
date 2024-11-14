package com.example.VehicleStore.service.impl.rental;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.rental.Rental;
import com.example.VehicleStore.repository.rantal.RentalRepository;
import com.example.VehicleStore.search.rental.RentalSearch;
import com.example.VehicleStore.search.rental.spec.RentalSpec;
import com.example.VehicleStore.search.sort.rental.RentalSort;
import com.example.VehicleStore.service.service.rental.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    final RentalRepository rentalRepository;


    @Override
    public Rental findById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Rental rental) {
        rentalRepository.save(rental);
    }

    @Override
    public List<Rental> findAll(RentalSearch search, Pagination pagination, RentalSort sort) {
        return rentalRepository.findAll(new RentalSpec(search), pagination.pageable(buildSort(sort)));
    }

    @Override
    public void softDelete(Long id) {
        final Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null){
            rental.setDeletionTime(LocalDateTime.now());
            rentalRepository.save(rental);
        }
    }

    private Sort buildSort(RentalSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case START_DATE_ASC, START_DATE_DESC -> "startDate";
            case RETURN_DATE_ASC, RETURN_DATE_DESC -> "returnDate";
            case EXPIRATION_DATE_ASC, EXPIRATION_DATE_DESC -> "expirationDate";
            case RENTAL_PRICE_ASC, RENTAL_PRICE_DESC -> "rentalPrice";
            case RENTAL_STATUS_ASC, RENTAL_STATUS_DESC -> "rentalStatus";
            case CLIENT_NAME_ASC, CLIENT_NAME_DESC -> "client.name";
            case CAR_BRAND_ASC, CAR_BRAND_DESC -> "car.brand";
            case MOTORCYCLE_BRAND_ASC, MOTORCYCLE_BRAND_DESC -> "motorcycle.brand";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }
}

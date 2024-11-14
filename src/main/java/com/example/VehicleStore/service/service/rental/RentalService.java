package com.example.VehicleStore.service.service.rental;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.rental.Rental;
import com.example.VehicleStore.search.rental.RentalSearch;
import com.example.VehicleStore.search.sort.rental.RentalSort;

import java.util.List;

public interface RentalService {

    Rental findById(Long id);

    void save(Rental rental);

    List<Rental> findAll(RentalSearch search, Pagination pagination, RentalSort sort);

    void softDelete(Long id);
}

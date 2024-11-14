package com.example.VehicleStore.controller.rental;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.dto.rental.ApiRental;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.rental.Rental;
import com.example.VehicleStore.entity.rental.enums.RentalStatus;
import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.rental.RentalSearch;
import com.example.VehicleStore.search.sort.rental.RentalSort;
import com.example.VehicleStore.service.service.car.CarBrandService;
import com.example.VehicleStore.service.service.car.CarService;
import com.example.VehicleStore.service.service.moto.MotoBrandService;
import com.example.VehicleStore.service.service.moto.MotoService;
import com.example.VehicleStore.service.service.rental.RentalService;
import com.example.VehicleStore.service.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RentalController {

    final RentalService rentalService;
    final CarService carService;
    final MotoService motoService;
    final UserService userService;
    final CarBrandService carBrandService;
    final MotoBrandService motoBrandService;


    @PostMapping("/rental")
    public void saveOrUpdateRental(@RequestBody @Valid ApiRental apiRental){
        final Rental rental;

        if (apiRental.getId() == null){
            rental = new Rental();
        }else {
            rental = rentalService.findById(apiRental.getId());
            if (rental == null){
                throw new NotFoundException("Rental not found");
            }
        }
        if (apiRental.getCar() != null) {
            rental.setCar(carService.findById(apiRental.getCar().getId()));
        }
        if (apiRental.getMotorcycle() != null){
            rental.setMotorcycle(motoService.findById(apiRental.getMotorcycle().getId()));
        }
        if (apiRental.getClient() != null){
            rental.setClient(userService.findById(apiRental.getClient().getId()));
        }
        if (apiRental.getRentalPrice() != null) {
            rental.setRentalPrice(apiRental.getRentalPrice());
        }
        if (apiRental.getRentalStatus() != null) {
            rental.setRentalStatus(apiRental.getRentalStatus());
        }
        if (apiRental.getStartDate() != null) {
            rental.setStartDate(apiRental.getStartDate());
        }
        if (apiRental.getReturnDate() != null) {
            rental.setReturnDate(apiRental.getReturnDate());
        }
        if (apiRental.getExpirationDate() != null) {
            rental.setExpirationDate(apiRental.getExpirationDate());
        }

        rentalService.save(rental);
    }


    @GetMapping("/rental/{id}")
    private ApiRental getById(@PathVariable Long id){
        final Rental rental = rentalService.findById(id);
        if (rental == null){
            throw new NotFoundException("Rental not found");
        }
        return new ApiRental(rental);
    }


    @GetMapping("/rentals")
    public List<ApiRental> getAll(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long motorcycleId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate startDateTo,
            @RequestParam(required = false) LocalDate startDateFrom,
            @RequestParam(required = false) LocalDate returnDate,
            @RequestParam(required = false) LocalDate returnDateTo,
            @RequestParam(required = false) LocalDate returnDateFrom,
            @RequestParam(required = false) LocalDate expirationDate,
            @RequestParam(required = false) LocalDate expirationDateTo,
            @RequestParam(required = false) LocalDate expirationDateFrom,
            @RequestParam(required = false) BigDecimal rentalPrice,
            @RequestParam(required = false) BigDecimal rentalPriceTo,
            @RequestParam(required = false) BigDecimal rentalPriceFrom,
            @RequestParam(required = false) RentalStatus rentalStatus,
            @RequestParam(required = false) Long carBrandId,
            @RequestParam(required = false) Long motoBrandId,
            @RequestParam(required = false) Integer carYear,
            @RequestParam(required = false) Integer motoYear,
            @RequestParam(required = false) RentalSort sort,
            Pagination pagination
            ){

        final RentalSearch search = new RentalSearch()
                .setStartDate(startDate)
                .setStartDateTo(startDateTo)
                .setStartDateFrom(startDateFrom)
                .setReturnDate(returnDate)
                .setReturnDateTo(returnDateTo)
                .setReturnDateFrom(returnDateFrom)
                .setExpirationDate(expirationDate)
                .setExpirationDateTo(expirationDateTo)
                .setExpirationDateFrom(expirationDateFrom)
                .setRentalPrice(rentalPrice)
                .setRentalPriceTo(rentalPriceTo)
                .setRentalPriceFrom(rentalPriceFrom)
                .setRentalStatus(rentalStatus)
                .setCarYear(carYear)
                .setMotoYear(motoYear);

        if (search.getCar() != null){
            final Car car = carService.findById(carId);
            if (car == null){
                throw new NotFoundException("Car not found.");
            }
            search.setCar(car);
        }
        if (search.getMotorcycle() != null){
            final Motorcycle motorcycle = motoService.findById(motorcycleId);
            if (motorcycle == null){
                throw new NotFoundException("Motorcycle not found.");
            }
            search.setMotorcycle(motorcycle);
        }
        if (search.getClient() != null){
            final User client = userService.findById(clientId);
            if (client == null){
                throw new NotFoundException("Client not found.");
            }
            search.setClient(client);
        }
        if (search.getCarBrand() != null){
            final CarBrand carBrand = carBrandService.findById(carBrandId);
            if (carBrand == null){
                throw new NotFoundException("Car Brand not found.");
            }
        }
        if (search.getMotoBrand() != null){
            final MotoBrand motoBrand = motoBrandService.findById(motoBrandId);
            if (motoBrand == null){
                throw new NotFoundException("Moto Brand not found.");
            }
            search.setMotoBrand(motoBrand);
        }


        final List<Rental> rentals = rentalService.findAll(search, pagination, sort);

        return rentals.stream().map(ApiRental::new).collect(Collectors.toList());
    }


    @DeleteMapping("/rental/{id}")
    public void deleteRental(@PathVariable Long id){
        rentalService.softDelete(id);
    }

}

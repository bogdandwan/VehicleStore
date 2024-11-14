package com.example.VehicleStore.search.rental;

import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.rental.enums.RentalStatus;
import com.example.VehicleStore.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class RentalSearch {


    private User client;
    private Car car;
    private Motorcycle motorcycle;
    private LocalDate startDate;
    private LocalDate startDateTo;
    private LocalDate startDateFrom;
    private LocalDate returnDate;
    private LocalDate returnDateTo;
    private LocalDate returnDateFrom;
    private LocalDate expirationDate;
    private LocalDate expirationDateTo;
    private LocalDate expirationDateFrom;
    private BigDecimal rentalPrice;
    private BigDecimal rentalPriceTo;
    private BigDecimal rentalPriceFrom;
    private RentalStatus rentalStatus;
    private CarBrand carBrand;
    private MotoBrand motoBrand;
    private Integer carYear;
    private Integer motoYear;

}

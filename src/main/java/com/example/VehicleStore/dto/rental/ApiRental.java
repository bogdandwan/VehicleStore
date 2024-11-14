package com.example.VehicleStore.dto.rental;

import com.example.VehicleStore.dto.car.ApiCar;
import com.example.VehicleStore.dto.moto.ApiMoto;
import com.example.VehicleStore.dto.user.ApiUser;
import com.example.VehicleStore.entity.rental.Rental;
import com.example.VehicleStore.entity.rental.enums.RentalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ApiRental {


    private Long id;
    private ApiCar car;
    private ApiUser client;
    private ApiMoto motorcycle;
    private LocalDate startDate;
    private LocalDate returnDate;
    private LocalDate expirationDate;
    private BigDecimal rentalPrice;
    private RentalStatus rentalStatus;


    public ApiRental(Rental rental){
        id = rental.getId();
        startDate = rental.getStartDate();
        returnDate = rental.getReturnDate();
        expirationDate = rental.getExpirationDate();
        rentalPrice = rental.getRentalPrice();
        rentalStatus = rental.getRentalStatus();

        if (rental.getCar() != null){
            car = new ApiCar(rental.getCar());
        }

        if (rental.getMotorcycle() != null){
            motorcycle = new ApiMoto(rental.getMotorcycle());
        }

        if (rental.getClient() != null){
            client = new ApiUser(rental.getClient());
        }
    }


}

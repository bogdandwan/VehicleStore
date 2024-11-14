package com.example.VehicleStore.entity.rental;

import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.rental.enums.RentalStatus;
import com.example.VehicleStore.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
@Accessors(chain = true)
@Data
public class Rental {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorcycle_id", nullable = false)
    private Motorcycle motorcycle;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "rental_expiration", nullable = false)
    @NotNull
    private LocalDate expirationDate;

    @Column(name = "rental_price", nullable = false)
    private BigDecimal rentalPrice;

    @Column(name = "rental_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}

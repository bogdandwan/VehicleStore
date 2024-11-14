package com.example.VehicleStore.entity.items.moto;

import com.example.VehicleStore.entity.items.engine.Engine;
import com.example.VehicleStore.entity.enums.Condition;
import com.example.VehicleStore.entity.items.moto.enums.Seats;
import com.example.VehicleStore.entity.items.moto.enums.Transmission;
import com.example.VehicleStore.entity.items.moto.enums.TypeBodyWork;
import com.example.VehicleStore.entity.rental.Rental;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "motorcycle")
@Data
@Accessors(chain = true)
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_brand_id", nullable = false)
    private MotoBrand motoBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_model_id", nullable = false)
    private MotoModel motoModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_engine_id", nullable = false)
    private Engine engine;

    @Enumerated
    @Column(name = "moto_condition", nullable = false)
    private Condition condition;

    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;

    @Column(name = "rental_price", nullable = false)
    private Integer rentalPrice;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "mileage", nullable = false)
    private Double mileage;

    @Column(name = "color", nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", nullable = false)
    private Transmission transmissionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_body_work", nullable = false)
    private TypeBodyWork typeBodyWork;

    @Enumerated(EnumType.STRING)
    @Column(name = "seats", nullable = false)
    private Seats seats;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @OneToMany(mappedBy = "motorcycle", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Rental> rentals;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;




}

package com.example.VehicleStore.entity.items.engine;

import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.engine.enums.EmissionStandard;
import com.example.VehicleStore.entity.items.engine.enums.Fuel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "engine")
@Data
@Accessors(chain = true)
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel", nullable = false)
    private Fuel fuel;

    @Column(name = "displacement", nullable = false)
    private Integer displacement;

    @Column(name = "kw_power", nullable = false)
    private Double kwPower;

    @Column(name = "horse_power", nullable = false)
    private Integer horsePower;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_standard")
    private EmissionStandard emissionStandard;

    @Column(name = "number_of_cylinders", nullable = false)
    private Integer numberOfCylinders;

    @Column(name = "fuel_consumption")
    private Double fuelConsumption;

    @OneToMany(mappedBy = "engine", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Car> cars;

    @Column(name = "deletionTime")
    private LocalDateTime deletionTime;


}

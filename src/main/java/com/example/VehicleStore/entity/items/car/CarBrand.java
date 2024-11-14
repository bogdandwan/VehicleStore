package com.example.VehicleStore.entity.items.car;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "car_brand")
@Data
@Accessors(chain = true)
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand_country", nullable = false)
    private String brandCountry;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL)
    private List<CarModel> carModels;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Car> cars;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}

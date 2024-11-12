package com.example.VehicleStore.entity.items.car;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "car_model")
@Data
@Accessors(chain = true)
public class CarModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Car> cars;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}

package com.example.VehicleStore.entity.items.moto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "moto_brand")
@Data
@Accessors(chain = true)
public class MotoBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand_country", nullable = false)
    private String brandCountry;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @OneToMany(mappedBy = "motoBrand", cascade = CascadeType.ALL)
    private List<MotoModel> motoModels;

    @OneToMany(mappedBy = "motoBrand", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Motorcycle> motorcycles;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}

package com.example.VehicleStore.entity.items.moto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "moto_model")
@Data
@Accessors(chain = true)
public class MotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "moto_brand_id")
    private MotoBrand motoBrand;

    @OneToMany(mappedBy = "motoModel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Motorcycle> motorcycles;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}

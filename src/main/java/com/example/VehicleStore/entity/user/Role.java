package com.example.VehicleStore.entity.user;

import com.example.VehicleStore.entity.user.enums.Privilege;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@Accessors(chain = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "privilege", columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Privilege.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_id"))
    private List<Privilege> privileges;

    @Column(name = "deletion_time")
    private LocalDateTime deletionTime;

}
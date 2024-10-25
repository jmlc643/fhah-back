package com.upao.pe.fhahback.models;

import com.upao.pe.fhahback.models.enums.Sizes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "talla")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talla")
    private Long idSize;

    @Column(name = "talla", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sizes size;

    // Relación Uno a Muchos con Inventory
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private List<Inventory> inventories;
}

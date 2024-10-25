package com.upao.pe.fhahback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_color")
    private Long idColor;

    @Column(name = "nombre_color", nullable = false)
    private String colorName;

    // Relación Uno a Muchos con Inventario
    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
    private List<Inventory> inventories;
}
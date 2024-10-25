package com.upao.pe.fhahback.models;

import com.upao.pe.fhahback.models.enums.Suppliers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "proveedor")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idSupplier;

    @Column(name = "nombre_prove", nullable = false)
    @Enumerated(EnumType.STRING)
    private Suppliers suppliersName;

    // Relación Uno a Muchos con Inventario
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Inventory> inventories;
}

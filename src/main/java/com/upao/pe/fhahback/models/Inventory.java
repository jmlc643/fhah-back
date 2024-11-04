package com.upao.pe.fhahback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "inventario")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventory;

    @Column(name = "codigo_barras", nullable = false, unique = true)
    private String barcode;

    @Column(name = "cantidad", nullable = false)
    private int quantity;

    // Relación Muchos a Uno con Proveedor
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Supplier supplier;

    // Relación Muchos a Uno con Talla
    @ManyToOne
    @JoinColumn(name = "id_talla", nullable = false)
    private Size size;

    // Relación Muchos a Uno con Color
    @ManyToOne
    @JoinColumn(name = "id_color", nullable = false)
    private Color color;

    // Relación Muchos a Uno con Producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product product;
}

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
@Entity(name = "producto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProduct;

    @Column(name = "codigo", nullable = false)
    private String code;

    @Column(name = "codigo_barras", nullable = false, unique = true)
    private String barcode;

    @Column(name = "precio_compra", nullable = false)
    private double purchasePrice;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "modelo")
    private String model;

    @Column(name = "precio_venta", nullable = false)
    private double salesPrice;

    @Column(name = "foto", nullable = false)
    private String photo;

    // Relación Uno a Muchos con Inventario
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    // Relación Uno a Muchos con Marca-Producto
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BrandProduct> brandProducts;
}

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
@Entity(name = "detalle_venta")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idSaleDetail;

    @Column(name = "cantidad", nullable = false)
    private int quantity;

    @Column(name = "precio_venta", nullable = false)
    private double salesPrice;

    // Relación Muchos a Uno con Venta
    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Sale sale;

    // Relación Muchos a Muchos con Producto
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Product.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "producto_detalle", joinColumns = @JoinColumn(name = "id_detalle"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<Product> products;
}

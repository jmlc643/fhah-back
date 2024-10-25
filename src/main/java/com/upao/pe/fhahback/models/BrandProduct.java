package com.upao.pe.fhahback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "marca_producto")
public class BrandProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca_producto")
    private Long idBrandProduct;

    @Column(name = "cantidad", nullable = false)
    private int quantity;

    // Relación Muchos a Uno con Marca
    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Brand brand;

    // Relación Muchos a Uno con Producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product product;
}

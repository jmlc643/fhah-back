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
@Entity(name = "marca")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idBrand;

    @Column(name = "nombre_marca", nullable = false)
    private String brandName;

    // Relación Uno a Muchos con Marca-Producto
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<BrandProduct> brandProducts;
}

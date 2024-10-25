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
@Entity(name = "cliente")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idClient;

    @Column(name = "nombre_cliente", nullable = false)
    private String clientName;

    @Column(name = "apellido_cliente", nullable = false)
    private String clientSurname;

    @Column(name = "dni", unique = true)
    private String dni;

    @Column(name = "ruc", unique = true)
    private String ruc;

    // Relación Uno a Muchos con Venta
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Sale> sales;
}

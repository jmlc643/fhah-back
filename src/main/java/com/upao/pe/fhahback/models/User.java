package com.upao.pe.fhahback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUser;

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String user;

    @Column(name = "password", nullable = false)
    private String password;

    // Relación Muchos a Uno con Role
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RoleUser roleUser;

    // Relación Uno a Muchos con Venta
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Sale> sales;
}

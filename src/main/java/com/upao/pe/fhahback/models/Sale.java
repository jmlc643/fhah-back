package com.upao.pe.fhahback.models;

import com.upao.pe.fhahback.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "venta")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idSale;

    @Column(name = "saldo", nullable = false)
    private double balance;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "importe_total", nullable = false)
    private double amount;

    // Relación Muchos a Uno con Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Client client;

    // Relación Muchos a Uno con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    // Relación Uno a Muchos con Detalle-Venta
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetail> saleDetails;
}

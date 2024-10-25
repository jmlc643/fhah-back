package com.upao.pe.fhahback.models;

import com.upao.pe.fhahback.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "rol_usuario")
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRoleUser;

    @Column(name = "nombre_rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role nameRol;

    // Relación Uno a Muchos con Usuario
    @OneToMany(mappedBy = "roleUser", cascade = CascadeType.ALL)
    private List<User> users;
}

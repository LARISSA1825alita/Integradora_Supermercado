package com.integradora.supermercadointegradora.Entity;

import jakarta.persistence.*;

@Entity
public class CarritoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Producto producto;

    private int cantidad;

    // Getters y setters
}

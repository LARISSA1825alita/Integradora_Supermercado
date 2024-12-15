package com.integradora.supermercadointegradora.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CarritoProducto> carrito;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<com.integradora.supermercadointegradora.entity.CarritoProducto> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<com.integradora.supermercadointegradora.entity.CarritoProducto> carrito) {
        this.carrito = carrito;
    }
}

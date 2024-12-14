package com.integradora.supermercadointegradora.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<CarritoProducto> carrito;

    // son los getters y los setters
    public Long getId() {
        //va a retorvnar el valor del id
        return id;
    }
    public void setId(Long id) {
        //se va a establece el valor dle id
        this.id = id;
    }
    public String getNombre() {
        //el nombbre va a retornar el nombre
        return nombre;
    }
    public void setNombre(String nombre) {
        //va a establecer el valor del nom
        this.nombre = nombre;
    }
    public List<CarritoProducto> getCarrito() {
        //retorna la lista de carrito producto
        return carrito;
    }
    public void setCarrito(List<CarritoProducto> carrito) {
        //establece el valor del carrito
        this.carrito = carrito;
    }
}


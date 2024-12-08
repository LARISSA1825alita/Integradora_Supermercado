package com.integradora.supermercadointegradora.Entity;
import jakarta.persistence.*;

//  clase asociada a una tabla en la base de datos
@Entity
public class Producto {

    // se defne un  atributo id como la clave primaria de la entidad
    @Id
    // se  genera  el valor del id usando autoincremento bd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // se define atributo que representa el nombre del producto
    private String nombre;

    // se define atributo que representa el precio del producto
    private double precio;

    // Getters y Setters
    // metodo para obtener el id del producto
    public Long getId() {
        return id;
    }

    // metodo para establecer el id del producto
    public void setId(Long id) {
        this.id = id;
    }

    // metodo para obtener el nombre del producto
    public String getNombre() {
        return nombre;
    }

    // metodo para establecer el nombre del producto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // metodo para obtener el precio del producto
    public double getPrecio() {
        return precio;
    }

    // metodo para establecer el precio del producto
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

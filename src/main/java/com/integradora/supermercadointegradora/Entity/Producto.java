package com.integradora.supermercadointegradora.Entity;

// Importaciones para trabjar con bd y id
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Producto {
    //clave primaria para la tabla de bd
    @Id
    // se usa para generar automaticamente en la bd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Definimos el nombre del producto tipo String
    private String nombre;
    //Definimos el precio del producto tipo duble
    private Double precio;

    // Getter para ibtener el valor de id
    public Long getId() {
        return id;
    }
    //Setter para establecer el valor
    public void setId(Long id) {
        this.id = id;
    }
    //el get ibtendra el valor
    public String getNombre() {
        return nombre;
    }
    //setter para establecer el valor
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //getter para obtener el valor
    public Double getPrecio() {
        return precio;
    }
    //setter para establecer el valor
    public void setPrecio(Double precio) {
        this.precio = precio;
 }
}


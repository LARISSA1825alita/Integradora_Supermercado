package com.integradora.supermercadointegradora.Entity;

// Importaciones para trabjar con bd y id
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos") // Especifica el nombre de la tabla si es necesario
public class Producto {

    @Id
    private Long id;

    private String nombre;
    private double precio;

    // Constructor, getters, setters y otros métodos

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
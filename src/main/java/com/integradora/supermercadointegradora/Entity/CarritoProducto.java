package com.integradora.supermercadointegradora.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    @ManyToOne // Relación ManyToOne con Producto
    @JoinColumn(name = "producto_id") // Especifica el nombre de la columna que se usará para la relación en la base de datos
    private Producto producto;

    @ManyToOne // Relación ManyToOne con Cliente
    private Cliente cliente;

    private int cantidad;

    // Constructor vacío (necesario para JPA)
    public CarritoProducto() {
    }

    // Constructor con parámetros
    public CarritoProducto(Producto producto, Cliente cliente, int cantidad) {
        this.producto = producto;
        this.cliente = cliente;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CarritoProducto{id=" + id + ", producto=" + producto + ", cliente=" + cliente + ", cantidad=" + cantidad + '}';
    }
}

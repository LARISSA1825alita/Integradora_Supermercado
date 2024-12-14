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

    private Integer cantidad;

    // los getters y los  setters
    // El id des Long y es de clave primaria en la tabla
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        //se establece el valor del id
        this.id = id;
    }
    public Cliente getCliente() {
        //va a retornar al cliente
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        //es el obj cliente que se asocia con el carrito
        this.cliente = cliente;
    }
    public Producto getProducto() {
        //va a retornar el obj de producto
        return producto;
    }
    public void setProducto(Producto producto) {
        //es el producto que se asocia con el carrito
        this.producto = producto;
    }
    public Integer getCantidad() {
        //retorna la cantidad de los productos en el carrito
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        //se establece la cantidad de los productos
        this.cantidad = cantidad;
    }
}
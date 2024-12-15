package com.integradora.supermercadointegradora.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "carrito_producto")
public class CarritoProducto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private com.integradora.supermercadointegradora.entity.Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private com.integradora.supermercadointegradora.entity.Producto producto;

    private Long cantidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.integradora.supermercadointegradora.entity.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(com.integradora.supermercadointegradora.entity.Cliente cliente) {
        this.cliente = cliente;
    }

    public com.integradora.supermercadointegradora.entity.Producto getProducto() {
        return producto;
    }

    public void setProducto(com.integradora.supermercadointegradora.entity.Producto producto) {
        this.producto = producto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }


}

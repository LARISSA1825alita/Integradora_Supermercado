package com.integradora.supermercadointegradora.Entity;
import jakarta.persistence.*;

//notacion para clase Jsa , tiene relacion con la tabla de la bd
@Entity
public class CarritoProducto {

    //se va a definir una clave primaria para la entidad
    @Id
    // se generara  el valor de la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // existe una relacion muchos a uno con la entidad Cliente, cada CarritoProducto esta asociado a un cliente
    @ManyToOne
    private Cliente cliente;

    // existe una relacon muchos a uno con la entidad Producto, cada CarritoProducto esta asociado a un producto
    @ManyToOne
    private Producto producto;

    // este atributo indica  la cantidad de un producto en el carrito
    private int cantidad;

    // Getters and Setters
    // metodo para obtener el id del carrito de producto
    public Long getId() {
        return id;
    }

    // metodo para establecer el id del carrito de producto
    public void setId(Long id) {
        this.id = id;
    }

    // metodo para obtener el cliente asociado al carrito de producto
    public Cliente getCliente() {
        return cliente;
    }

    // metodo para establecer el cliente asociado al carrito de producto
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // metodo para obtener el producto asociado al carrito de producto
    public Producto getProducto() {
        return producto;
    }

    // metodo para establecer el producto asociado al carrito de producto
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // metodo para obtener la cantidad del producto en el carrito
    public int getCantidad() {
        return cantidad;
    }

    // metodo para establecer la cantidad del producto en el carrito
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

package com.integradora.supermercadointegradora.Entity;
import jakarta.persistence.*;
import java.util.List;

//  clase c asociada a una tabla en la base de datos
@Entity
public class Cliente {

    // Se define un atributo id como la clave primaria de la entidad
    @Id
    //se genera el valor del id usando autoincremento en la bd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // se define atributo que representa el nombre del cliente
    private String nombre;

    // existe una relacion uno a muchos con la entidad CarritoProducto
    // 'mappedBy' indica que la relación est mapeada en el atributo 'cliente' de la clase CarritoProducto
    @OneToMany(mappedBy = "cliente")
    private List<CarritoProducto> carrito;

    // Getters y Setters
    // metodo para obtener el id del cliente
    public Long getId() {
        return id;
    }

    // metodo para establecer el id del cliente
    public void setId(Long id) {
        this.id = id;
    }

    // mertodo para obtener el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // metodo para establecer el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // metodo para obtener la lista de productos en el carrito del cliente
    public List<CarritoProducto> getCarrito() {
        return carrito;
    }

    // metodo para establecer la lista de productos en el carrito del cliente
    public void setCarrito(List<CarritoProducto> carrito) {
        this.carrito = carrito;
    }
}

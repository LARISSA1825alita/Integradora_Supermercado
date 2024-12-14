package com.integradora.supermercadointegradora.controller;

import org.springframework.web.bind.annotation.*;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Custom.CustomStack;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
//pila para los productos del carrito
    private CustomStack<CarritoProducto> carritoStack = new CustomStack<>();

    //Edpoint para agregar un producto al carrito
    @PostMapping("/agregar")
    public String agregarProducto(@RequestBody CarritoProducto carritoProducto) {
        //el push agrega un producto en la pila
        carritoStack.push(carritoProducto);
        //retorna un mensaje de confirmacion
        return "Producto agregado al carrito";
    }
    //Edpoint para obener los productos del carrito con un cliente especifico
    @GetMapping("/{clienteId}")
    public List<CarritoProducto> obtenerCarrito(@PathVariable Long clienteId) {
    //va a devolver la pila con los productos
        return (List<CarritoProducto>) carritoStack.getStack();
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestBody CarritoProducto carritoProducto) {
    //Eliminar el ultimo producto que se agrego
        carritoStack.pop();
        //retorna un mensaje de confirmacion

        return "El producto se elimino del carrito";
    }
    //Edpoint para deshacer la ultima eliminacion
    @PostMapping("/deshacer")
    public String deshacerEliminacion() {
        //retorna un mensaje de confirmacion
        return "Deshacer la ultima eliminacion";
    }
}


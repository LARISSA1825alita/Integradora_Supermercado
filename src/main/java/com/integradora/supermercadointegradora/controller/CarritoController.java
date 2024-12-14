package com.integradora.supermercadointegradora.controller;

import org.springframework.web.bind.annotation.*;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Custom.CustomStack;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private CustomStack<CarritoProducto> carritoStack = new CustomStack<>();

    @PostMapping("/agregar")
    public String agregarProducto(@RequestBody CarritoProducto carritoProducto) {
        carritoStack.push(carritoProducto);
        return "Producto agregado al carrito";
    }

    @GetMapping("/{clienteId}")
    public List<CarritoProducto> obtenerCarrito(@PathVariable Long clienteId) {
        // Este ejemplo devuelve el stack completo de productos para el cliente
        return (List<CarritoProducto>) carritoStack.getStack();
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestBody CarritoProducto carritoProducto) {
        carritoStack.pop();
        return "Producto eliminado del carrito";
    }

    @PostMapping("/deshacer")
    public String deshacerEliminacion() {
        // Implementar recuperación del último producto eliminado si es necesario
        return "Deshacer última eliminación";
    }
}


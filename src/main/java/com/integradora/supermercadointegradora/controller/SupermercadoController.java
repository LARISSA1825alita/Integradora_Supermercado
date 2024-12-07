package com.integradora.supermercadointegradora.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.integradora.supermercadointegradora.Service.SupermercadoService;
import java.util.List;

@RestController
@RequestMapping("/api/supermercado")
public class SupermercadoController {

    @Autowired
    private SupermercadoService supermercadoService;

    // Agregar un producto al carrito de un cliente
    @PostMapping("/agregarProducto")
    public String agregarProductoAlCarrito(@RequestParam Long clienteId, @RequestParam Long productoId, @RequestParam int cantidad) {
        return supermercadoService.agregarProductoAlCarrito(clienteId, productoId, cantidad);
    }

    // Agui agrega uno
    // Eliminar un producto del carrito de un cliente
    @DeleteMapping("/eliminarProducto")
    public String eliminarProductoDelCarrito(@RequestParam Long clienteId, @RequestParam Long productoId) {
        return supermercadoService.eliminarProductoDelCarrito(clienteId, productoId);
 }
}
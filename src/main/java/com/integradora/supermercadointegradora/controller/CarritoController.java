package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Service.CarritoProductoService;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoProductoService carritoProductoService;

    // Endpoint para agregar un producto al carrito
    @PostMapping("/agregar")
    public String agregarProductoAlCarrito(@RequestParam Long clienteId, @RequestParam Long productoId, @RequestParam int cantidad) {
        CarritoProducto carritoProducto = carritoProductoService.agregarProductoAlCarrito(clienteId, productoId, cantidad);
        if (carritoProducto != null) {
            return "Producto agregado al carrito.";
        }
        return "Hubo un problema al agregar el producto.";
    }

}

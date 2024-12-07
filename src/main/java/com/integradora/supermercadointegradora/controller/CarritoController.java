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
    @GetMapping("/productos")
    public List<CarritoProducto> obtenerProductosEnCarrito(@RequestParam Long clienteId) {
        List<CarritoProducto> productos = carritoProductoService.obtenerProductosEnCarrito(clienteId);
        if (productos != null && !productos.isEmpty()) {
            return productos;
        }
        return null;
}
    // Endpoint para eliminar un producto del carrito
    @DeleteMapping("/eliminar")
    public String eliminarProductoDelCarrito(@RequestParam Long carritoProductoId) {
        boolean eliminado = carritoProductoService.eliminarProductoDelCarrito(carritoProductoId);
        if (eliminado) {
            return "Producto eliminado del carrito.";
        }
        return "El producto no fue encontrado en el carrito.";
    }
    // Endpoint para deshacer la ultima eliminación de un producto
    @PostMapping("/deshacerEliminacion")
    public String deshacerUltimaEliminacion() {
        CarritoProducto productoRecuperado = carritoProductoService.deshacerUltimaEliminacion();
        if (productoRecuperado != null) {
            return "Última eliminación deshecha, producto agregado nuevamente al carrito.";
        }
        return "No hay eliminaciones para deshacer.";
 }
}


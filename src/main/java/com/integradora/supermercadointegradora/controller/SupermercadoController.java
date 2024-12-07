package com.integradora.supermercadointegradora.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.integradora.supermercadointegradora.Service.SupermercadoService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/supermercado")
public class SupermercadoController {

    @Autowired
    private SupermercadoService supermercadoService;

    // Agregar un producto al carrito
    @PostMapping("/agregarProducto")
    public String agregarProductoAlCarrito(@RequestParam Long clienteId, @RequestParam Long productoId, @RequestParam int cantidad) {
        return supermercadoService.agregarProductoAlCarrito(clienteId, productoId, cantidad);
    }

    // Agui agrega uno
    // se elimina un producto del carrito del cliente
    @DeleteMapping("/eliminarProducto")
    public String eliminarProductoDelCarrito(@RequestParam Long clienteId, @RequestParam Long productoId) {
        return supermercadoService.eliminarProductoDelCarrito(clienteId, productoId);
 }

    // se va a deshacer la ultima eliminación de un producto
    @PostMapping("/deshacerEliminacion")
    public String deshacerUltimaEliminacion(@RequestParam Long clienteId) {
        return supermercadoService.deshacerUltimaEliminacion(clienteId);
    }
    // se van a obtener todos los productos
    @GetMapping("/carrito")
    public List<?> obtenerCarrito(@RequestParam Long clienteId) {
        return supermercadoService.obtenerCarrito(clienteId);
 }
    // se van a obtener el total de la compra
    @GetMapping("/totalCarrito")
    public double obtenerTotalCarrito(@RequestParam Long clienteId) {
        return supermercadoService.obtenerTotalCarrito(clienteId);
    }
    // se va a continuar con la compra del cliente
    @PostMapping("/procesarCompra")
    public String procesarCompra(@RequestParam Long clienteId) {
        return supermercadoService.procesarCompra(clienteId);
 }
}

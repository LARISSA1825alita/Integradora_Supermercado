package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.integradora.supermercadointegradora.Entity.*;
import com.integradora.supermercadointegradora.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supermercado")
public class SupermercadoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    // Procesar compra de un cliente
    @PostMapping("/comprar/{clienteId}")
    public String procesarCompra(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            List<CarritoProducto> carrito = carritoProductoRepository.findByCliente(cliente.get());
            if (carrito.isEmpty()) {
                return "El carrito está vacío, no se puede procesar la compra.";
            } else {
                // Simular el procesamiento de la compra
                carritoProductoRepository.deleteAll(carrito);
                return "Compra procesada para el cliente con ID: " + clienteId;
            }
        }
        return "Cliente no encontrado.";
    }

    // Obtener todos los productos disponibles para la venta
    @GetMapping("/productos")
    public List<Producto> obtenerProductosDisponibles() {
        return productoRepository.findAll();
    }

    // Agregar producto al carrito de un cliente
    @PostMapping("/agregarProductoCarrito/{clienteId}")
    public String agregarProductoCarrito(@PathVariable Long clienteId, @RequestBody CarritoProducto carritoProducto) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        Optional<Producto> producto = productoRepository.findById(carritoProducto.getProducto().getId());

        if (cliente.isPresent() && producto.isPresent()) {
            carritoProducto.setCliente(cliente.get());
            carritoProducto.setProducto(producto.get());
            carritoProductoRepository.save(carritoProducto);
            return "Producto agregado al carrito.";
        }
        return "Cliente o producto no encontrado.";
    }

    // Ver el carrito de un cliente
    @GetMapping("/verCarrito/{clienteId}")
    public List<CarritoProducto> verCarrito(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            return carritoProductoRepository.findByCliente(cliente.get());
        }
        return null; // O retornar un error adecuado
    }

    // Eliminar producto del carrito de un cliente
    @DeleteMapping("/eliminarProductoCarrito/{clienteId}/{productoId}")
    public String eliminarProductoCarrito(@PathVariable Long clienteId, @PathVariable Long productoId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        Optional<Producto> producto = productoRepository.findById(productoId);

        if (cliente.isPresent() && producto.isPresent()) {
            CarritoProducto carritoProducto = carritoProductoRepository.findByClienteAndProducto(cliente.get(), producto.get());
            if (carritoProducto != null) {
                carritoProductoRepository.delete(carritoProducto);
                return "Producto eliminado del carrito.";
            }
            return "Producto no encontrado en el carrito.";
        }
        return "Cliente o producto no encontrado.";
    }
}

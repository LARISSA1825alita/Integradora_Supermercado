package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo producto
    @PostMapping("/crear")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    @GetMapping("/todos")
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public Optional<Producto> obtenerProducto(@PathVariable Long id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto
    @PutMapping("/actualizar/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    // Eliminar un producto
    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}


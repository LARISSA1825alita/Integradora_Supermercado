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

    // Se va a crear un nuevo producto
    @PostMapping("/crear")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Se van a obtener todos los productos
    @GetMapping("/todos")
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    // Se va a obtener un producto por su id
    @GetMapping("/{id}")
    public Optional<Producto> obtenerProducto(@PathVariable Long id) {
        return productoRepository.findById(id);
    }

    // Se va a actualizar un producto
    @PutMapping("/actualizar/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    // Se va a eliminar un producto
    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}


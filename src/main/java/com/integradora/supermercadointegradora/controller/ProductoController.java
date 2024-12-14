package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/agregarProducto")
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }
}


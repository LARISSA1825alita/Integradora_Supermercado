package com.integradora.supermercadointegradora.controller;


import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/agregarProducto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @GetMapping("/listarProductos")
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoRepository.findAll());
    }
}
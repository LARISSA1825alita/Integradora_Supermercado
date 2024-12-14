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

    //este metodo es paraa agregar un producto nuevo
    @PostMapping("/agregarProducto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
    // se va a gardar  el producto recibido por  el cuerpo
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    //metodo para poder listar todos los productos
    @GetMapping("/listarProductos")
    public ResponseEntity<List<Producto>> listarProductos() {
    //ahora va a devolver a todos los productos que ya se guardaron
        return ResponseEntity.ok(productoRepository.findAll());
    }
}
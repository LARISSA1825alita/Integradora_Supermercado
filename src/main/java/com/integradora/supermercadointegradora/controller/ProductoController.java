package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// solo se permite   desde localhost:63342
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoint para agregar un nuevo producto
    @PostMapping("/agregarProducto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        try {
            // agregar el producto dessde el servicio
            Producto productoGuardado = productoService.agregarProducto(producto);

            // va a retornar el producto guardado
            return ResponseEntity.ok().body(productoGuardado);
        } catch (Exception e) {
            // se retorna error si es que lo hay
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}


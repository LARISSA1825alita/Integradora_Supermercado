package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoint para agregar un nuevo producto
    @PostMapping("/agregarProducto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.agregarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
 }
}
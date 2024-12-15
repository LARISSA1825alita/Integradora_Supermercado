package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.entity.Producto;
import com.integradora.supermercadointegradora.response.ProductoResponseRest;
import com.integradora.supermercadointegradora.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ProductoController {

    @Autowired
    private IProductoService service;
    @PostMapping("/producto/agregarProducto")
    public ResponseEntity<ProductoResponseRest> crear(@RequestBody Producto request){
        ResponseEntity<ProductoResponseRest>  response = service.crear(request);
        return response;
    }
    @GetMapping("/producto/listado")
    public ResponseEntity<ProductoResponseRest> listado(){
        ResponseEntity<ProductoResponseRest> response = service.buscarproducto();
        return response;
    }

}

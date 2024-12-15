package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.entity.CarritoProducto;
import com.integradora.supermercadointegradora.response.CarritoProductoResponseRest;
import com.integradora.supermercadointegradora.service.ICarritoProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CarritoProductoController {
    @Autowired
    private ICarritoProductoService service;

    @PostMapping("/carrito/agregar")
    public ResponseEntity<CarritoProductoResponseRest> agregarAlCarrito(@RequestBody CarritoProducto request){
        ResponseEntity<CarritoProductoResponseRest>  response = service.agregarAlCarrito(request);
        return response;
    }

    @GetMapping("/carrito/{clienteId}")
    public ResponseEntity<CarritoProductoResponseRest> consultarCarrito(@PathVariable Long clienteId){
        ResponseEntity<CarritoProductoResponseRest> response = service.consultarCarrito(clienteId);
        return response;
    }

    @PostMapping("/carrito/eliminar")
    public ResponseEntity<CarritoProductoResponseRest> eliminarProducto(@RequestBody CarritoProducto request) {
        return service.eliminarProducto(request);
    }

    @PostMapping("/carrito/deshacer")
    public ResponseEntity<CarritoProductoResponseRest> deshacerEliminarProducto() {
        return service.deshacerEliminarProducto();
    }



}

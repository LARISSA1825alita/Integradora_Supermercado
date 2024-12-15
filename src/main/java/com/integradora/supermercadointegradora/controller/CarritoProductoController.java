package com.integradora.supermercadointegradora.controller;

import org.example.estructuradedatos4f.entity.CarritoProducto;
import org.example.estructuradedatos4f.response.CarritoProductoResponseRest;
import org.example.estructuradedatos4f.service.ICarritoProductoService;
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

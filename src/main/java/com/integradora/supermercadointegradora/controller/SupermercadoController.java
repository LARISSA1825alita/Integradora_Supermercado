package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.response.CarritoProductoResponseRest;
import com.integradora.supermercadointegradora.service.ICarritoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SupermercadoController {
    @Autowired
    private ICarritoProductoService service;

    @GetMapping("/supermercado/comprar/{clienteId}")
    public ResponseEntity<CarritoProductoResponseRest> comprarCarrito(@PathVariable Long clienteId){
        ResponseEntity<CarritoProductoResponseRest> response = service.comprarCarrito(clienteId);
        return response;
    }

}

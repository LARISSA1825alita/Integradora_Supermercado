package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.entity.Cliente;
import com.integradora.supermercadointegradora.response.ClienteResponseRest;
import com.integradora.supermercadointegradora.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CajaController {
    @Autowired
    private IClienteService service;


    @PostMapping("/caja/agregar/{id}")
    public ResponseEntity<ClienteResponseRest> agregarCaja(@PathVariable Long id){
        ResponseEntity<ClienteResponseRest> response = service.agregarCaja(id);
        return response;
    }
    @GetMapping("/caja/atender")
    public ResponseEntity<ClienteResponseRest> atenderCaja(){
        ResponseEntity<ClienteResponseRest> response = service.atenderCaja();
        return response;
    }
    @GetMapping("/caja/obtenerFila")
    public ResponseEntity<ClienteResponseRest> consultarCaja(){
        ResponseEntity<ClienteResponseRest> response = service.consultarCaja();
        return response;
    }



}

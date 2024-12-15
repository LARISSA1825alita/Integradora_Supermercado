package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.entity.Cliente;
import com.integradora.supermercadointegradora.response.ClienteResponseRest;
import com.integradora.supermercadointegradora.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ClienteController {
    @Autowired
    private IClienteService service;

    @PostMapping("/cliente/agregarCliente")
    public ResponseEntity<ClienteResponseRest> crearCliente(@RequestBody Cliente request){
        ResponseEntity<ClienteResponseRest>  response = service.crearCliente(request);
        return response;
    }

    @GetMapping("/cliente/listado")
    public ResponseEntity<ClienteResponseRest> listado(){
        ResponseEntity<ClienteResponseRest> response = service.buscarclientes();
        return response;
    }
}

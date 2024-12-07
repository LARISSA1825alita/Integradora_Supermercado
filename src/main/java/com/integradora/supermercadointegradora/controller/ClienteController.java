package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para registrar un nuevo cliente
    @PostMapping("/agregarCliente")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.agregarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }
}

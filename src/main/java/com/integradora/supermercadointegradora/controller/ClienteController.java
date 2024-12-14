package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/agregarCliente")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @GetMapping("/listarClientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }
}
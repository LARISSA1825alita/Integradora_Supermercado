package com.integradora.supermercadointegradora.controller;
//// Se importan las clases
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

    // metodo para agregar un nuevo cliente
    @PostMapping("/agregarCliente")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        // se va a guardar el cliente que entro
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    //metodo para listar todos los clientes
    @GetMapping("/listarClientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
    //va a devolver todos los clientes que se guardaron
        return ResponseEntity.ok(clienteRepository.findAll());
    }
}
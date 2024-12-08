package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//definir las clase como controlador rest
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    // metodo para agregar un cliente
    @PostMapping("/agregarCliente")
    // va a recibir  un objeto Cliente en el cuerpo
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        // se va a llamar al servicio para agregar el nuevo cliente
        Cliente nuevoCliente = clienteService.agregarCliente(cliente);
        // Se va a retornar la respuesta con el nuevo cliente agregado
        return ResponseEntity.ok(nuevoCliente);
    }
}


package com.integradora.supermercadointegradora.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Queue;

@RestController
@RequestMapping("/caja")
public class CajaController {

    private Queue<Long> filaClientes = new LinkedList<>();

    @PostMapping("/agregar")
    public ResponseEntity<Void> agregarClienteAFila(@RequestParam Long clienteId) {
        filaClientes.offer(clienteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/atender")
    public ResponseEntity<Long> atenderCliente() {
        Long clienteId = filaClientes.poll();
        return clienteId != null ? ResponseEntity.ok(clienteId) : ResponseEntity.noContent().build();
    }

    @GetMapping("/obtenerFila")
    public ResponseEntity<Queue<Long>> obtenerFila() {
        return ResponseEntity.ok(filaClientes);
    }
}

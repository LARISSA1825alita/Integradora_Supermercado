package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/caja")
public class CajaController {
    private final Stack<Cliente> fila = new Stack<>();

    @PostMapping("/agregar")
    public String agregarClienteAFila(@RequestBody Cliente cliente) {
        fila.push(cliente);
        return "Cliente agregado a la fila";
    }

    @GetMapping("/atender")
    public Cliente atenderCliente() {
        return fila.isEmpty() ? null : fila.pop();
    }

    @GetMapping("/obtenerFila")
    public List<Cliente> obtenerFila() {
        return new ArrayList<>(fila);
    }
}
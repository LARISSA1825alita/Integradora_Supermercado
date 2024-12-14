package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.Custom.CustomQueue;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/caja")
public class CajaController {

    private CustomQueue<Cliente> filaClientes = new CustomQueue<>();

    @PostMapping("/agregar")
    public String agregarCliente(@RequestBody Cliente cliente) {
        filaClientes.enqueue(cliente);
        return "Cliente agregado a la fila";
    }

    @GetMapping("/atender")
    public Cliente atenderCliente() {
        return filaClientes.dequeue();
    }

    @GetMapping("/obtenerFila")
    public Queue<Cliente> obtenerFila() {
        return filaClientes.getQueue();
    }
}

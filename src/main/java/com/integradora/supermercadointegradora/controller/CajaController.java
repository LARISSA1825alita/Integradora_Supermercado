package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.Custom.CustomQueue;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/caja")
public class CajaController {
    // se crea una instancia  de la cola personalizada para gestionar los clientes
    private CustomQueue<Cliente> filaClientes = new CustomQueue<>();

    //Edpoint para agregar a uncliente a la cola, se recibe como json
    @PostMapping("/agregar")
    public String agregarCliente(@RequestBody Cliente cliente) {
        // se agregara al cliente  a la cola
        filaClientes.enqueue(cliente);
        //retorna un mensaje para confirmar
        return "El cliente se agrego a la fila";
    }

    //Edpoint para atender un cliente que este al frente de la cola , se recibe como json en la soli
    @GetMapping("/atender")
    public Cliente atenderCliente() {
        // se quita  y devuelve el cliente al frente de la cola
        return filaClientes.dequeue();
    }
    //Edpoint para obtener el estado de la fila
    @GetMapping("/obtenerFila")
    public Queue<Cliente> obtenerFila() {
        //va a devolver la cola actual con todos los clientes que ahi se encuentren
        return filaClientes.getQueue();
    }
}

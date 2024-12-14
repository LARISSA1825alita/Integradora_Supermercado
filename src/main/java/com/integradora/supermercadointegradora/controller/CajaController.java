package com.integradora.supermercadointegradora.controller;


import com.integradora.supermercadointegradora.Custom.CustomQueue;
import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/caja")

public class CajaController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    private CustomQueue<Cliente> filaClientes = new CustomQueue<>();

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAFila(@RequestBody Cliente cliente) {
        //va a agregar un cliente a la cola
        filaClientes.enqueue(cliente);
        return ResponseEntity.ok("El cliente se agrego a la fila para la caja");
    }

    //este metodo va a atender a la siguiente cliente
    @GetMapping("/atender")
    public ResponseEntity<String> atenderCliente() {
        //y se va a obtener al cliente que sigue
        Cliente clienteAtendido = filaClientes.dequeue();
        //si no hay ningun cliente en la fila se va a mandar un error
        if (clienteAtendido == null) {
            return ResponseEntity.badRequest().body("La fila n tiene ningun cliente");
        }
        //va a retornar el cliente que se esta atendiendo
        return ResponseEntity.ok("Cliente atendido " + clienteAtendido.getNombre());
    }
}

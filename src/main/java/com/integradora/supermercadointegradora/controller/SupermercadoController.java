package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.*;
import com.integradora.supermercadointegradora.repository.*;
import com.integradora.supermercadointegradora.Custom.CustomStack;
import com.integradora.supermercadointegradora.Custom.CustomQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/supermercado")

public class SupermercadoController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    //estas pila y cola van a manejar el historial y fila de los clientes
    private CustomStack<CarritoProducto> historialEliminados = new CustomStack<>();
    private CustomQueue<Cliente> filaClientes = new CustomQueue<>();

    //este metodo va a procesar toda la compra de un cliente
    @PostMapping("/comprar/{clienteId}")
    public ResponseEntity<String> procesarCompra(@PathVariable Long clienteId) {
        //aqui se va a buscar el cliente por el id
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        // este if va a ver si no se encuentra el cliente y retornaria un error
        if (cliente == null) {
            return ResponseEntity.badRequest().body("No se encontro al cliente");
        }
        //Va a buscar los productos del caarrito del cliente
        List<CarritoProducto> carrito = carritoProductoRepository.findByClienteId(clienteId);
        //si el carrito no esta lleno va a mandar un error
        if (carrito.isEmpty()) {
            return ResponseEntity.badRequest().body("");
        }
        //va a calcular el total  de la compra
        double total = 0;
        // este for
        for (CarritoProducto item : carrito) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        //va a eliminar todos los productos del carrito
        carritoProductoRepository.deleteAll(carrito);
        // y se va a devolver una respuesta con el total de la compra
        return ResponseEntity.ok("La compra fue exitosa , el total a pagar es: $" + total);
    }

    @GetMapping("/obtenerFila")
    public ResponseEntity<?> obtenerFila() {
        try {
            // Obtén la lista actual de clientes
            LinkedList<Cliente> linkedList = (LinkedList<Cliente>) filaClientes.getQueue();

            // Convierte la LinkedList a un CustomQueue
            CustomQueue<Cliente> customQueue = new CustomQueue<>();
            customQueue.getQueue().addAll(linkedList);
            // Devuelve el CustomQueue convertido
            return ResponseEntity.ok(customQueue.getQueue());
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al convertir la cola de clientes: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


}
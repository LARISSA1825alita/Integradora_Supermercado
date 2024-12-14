package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Entity.*;
import com.integradora.supermercadointegradora.repository.*;
import com.integradora.supermercadointegradora.Custom.CustomStack;
import com.integradora.supermercadointegradora.Custom.CustomQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private CustomStack<CarritoProducto> historialEliminados = new CustomStack<>();
    private CustomQueue<Cliente> filaClientes = new CustomQueue<>();

    @PostMapping("/comprar/{clienteId}")
    public ResponseEntity<String> procesarCompra(@PathVariable Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente no encontrado.");
        }

        List<CarritoProducto> carrito = carritoProductoRepository.findByClienteId(clienteId);

        if (carrito.isEmpty()) {
            return ResponseEntity.badRequest().body("El carrito está vacío.");
        }

        double total = 0;
        for (CarritoProducto item : carrito) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }

        carritoProductoRepository.deleteAll(carrito);

        return ResponseEntity.ok("Compra procesada. Total: $" + total);
    }

    @PostMapping("/carrito/eliminar")
    public ResponseEntity<String> eliminarProductoDelCarrito(@RequestBody CarritoProducto carritoProducto) {
        historialEliminados.push(carritoProducto);
        carritoProductoRepository.deleteById(carritoProducto.getId());
        return ResponseEntity.ok("Producto eliminado del carrito y guardado en el historial.");
    }

    @PostMapping("/carrito/deshacer")
    public ResponseEntity<String> deshacerEliminacion() {
        CarritoProducto ultimoEliminado = historialEliminados.pop();
        if (ultimoEliminado == null) {
            return ResponseEntity.badRequest().body("No hay elementos para deshacer.");
        }
        carritoProductoRepository.save(ultimoEliminado);
        return ResponseEntity.ok("Última eliminación deshecha con éxito.");
    }

    @PostMapping("/caja/agregar")
    public ResponseEntity<String> agregarAFila(@RequestBody Cliente cliente) {
        filaClientes.enqueue(cliente);
        return ResponseEntity.ok("Cliente agregado a la fila de la caja.");
    }

    @GetMapping("/caja/atender")
    public ResponseEntity<String> atenderCliente() {
        Cliente clienteAtendido = filaClientes.dequeue();
        if (clienteAtendido == null) {
            return ResponseEntity.badRequest().body("No hay clientes en la fila.");
        }
        return ResponseEntity.ok("Cliente atendido: " + clienteAtendido.getNombre());
    }


}
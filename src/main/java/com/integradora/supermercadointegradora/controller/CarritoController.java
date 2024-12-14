package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    private Stack<CarritoProducto> historialEliminados = new Stack<>();

    @PostMapping("/agregar")
    public CarritoProducto agregarProductoAlCarrito(@RequestBody CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    @GetMapping("/{clienteId}")
    public List<CarritoProducto> listarProductos(@PathVariable Long clienteId) {
        return carritoProductoRepository.findAll();
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestBody Long carritoProductoId) {
        CarritoProducto carritoProducto = carritoProductoRepository.findById(carritoProductoId).orElse(null);
        if (carritoProducto != null) {
            historialEliminados.push(carritoProducto);
            carritoProductoRepository.delete(carritoProducto);
            return "Producto eliminado";
        }
        return "Producto no encontrado";
    }

    @PostMapping("/deshacer")
    public String deshacerEliminacion() {
        if (!historialEliminados.isEmpty()) {
            CarritoProducto carritoProducto = historialEliminados.pop();
            carritoProductoRepository.save(carritoProducto);
            return "Eliminación deshecha";
        }
        return "No hay eliminaciones para deshacer";
    }
}


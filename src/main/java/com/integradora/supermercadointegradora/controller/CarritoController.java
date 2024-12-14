package com.integradora.supermercadointegradora.controller;

import com.integradora.supermercadointegradora.Custom.CustomQueue;
import com.integradora.supermercadointegradora.Custom.CustomStack;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")

public class CarritoController {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    private CustomStack<CarritoProducto> historialEliminados = new CustomStack<>();
    private CustomStack<CarritoProducto> carritoStack = new CustomStack<>();


    //Edpoint para agregar un producto al carrito
    @PostMapping("/agregar")
    public String agregarProducto(@RequestBody CarritoProducto carritoProducto) {
        //el push agrega un producto en la pila
        carritoStack.push(carritoProducto);
        //retorna un mensaje de confirmacion
        return "Producto agregado al carrito";
    }
    //Edpoint para obener los productos del carrito con un cliente especifico
    @GetMapping("/{clienteId}")
    public List<CarritoProducto> obtenerCarrito(@PathVariable Long clienteId) {
        //va a devolver la pila con los productos
        return (List<CarritoProducto>) carritoStack.getStack();
    }

    @PostMapping("/eliminar")
    public ResponseEntity<String> eliminarProductoDelCarrito(@RequestBody CarritoProducto carritoProducto) {
        // se va a guardar el producto eliminado en el historial
        historialEliminados.push(carritoProducto);
        //elimina el producto del carrito
        carritoProductoRepository.deleteById(carritoProducto.getId());
        return ResponseEntity.ok("El producto ya se elimino del carrito y se guardo al historial");
    }

    //este metodo se va a encargar de deshacer la ultima elimininacion que se hizo
    @PostMapping("/deshacer")
    public ResponseEntity<String> deshacerEliminacion() {
        //va a obtener el ultimo producto eliminado del historial
        CarritoProducto ultimoEliminado = historialEliminados.pop();

        //si no hay ningun elemento va a mandar un error
        if (ultimoEliminado == null) {
            return ResponseEntity.badRequest().body("No hay nada para deshacer");
        }
        //vuelve a agregar el producto al carrito
        carritoProductoRepository.save(ultimoEliminado);
        return ResponseEntity.ok("Se deciso la ultima eliminacon");
    }
}
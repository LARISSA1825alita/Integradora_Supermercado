package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
   //metodo para agreagar productos al carrito
    @PostMapping("/agregar")
    // recibira un objeto CarritoProducto en el body de la solicitud
    public ResponseEntity<CarritoProducto> agregarProducto(@RequestBody CarritoProducto carritoProducto) {
        // se va a llamar al servicio para agregar el producto al carrito
        CarritoProducto productoAgregado = carritoService.agregarProducto(carritoProducto);
        // va a retornar la respuesta con el producto agregado
        return ResponseEntity.ok(productoAgregado);
    }
    // metodo para obtener el carrito de un cliente especifico
    @GetMapping("/{clienteId}")
    // va a recibir el id del cliente como variable de ruta
    public ResponseEntity<List<CarritoProducto>> obtenerCarrito(@PathVariable Long clienteId) {
        // va a llamar al servicio para obtener los productos del carrito
        List<CarritoProducto> carrito = carritoService.obtenerCarrito(clienteId);
        // va a retornar la lista de productos en el carrito
        return ResponseEntity.ok(carrito);
    }
    // metodo para eliminar un producto del carrito
    @PostMapping("/eliminar")
    // va a recibir un objeto CarritoProducto en el body
    public ResponseEntity<Void> eliminarProducto(@RequestBody CarritoProducto carritoProducto) {
        // se va a llamer al servicio para eliminar el producto del carrito
        carritoService.eliminarProducto(carritoProducto);
        // va a retorna una respuesta
        return ResponseEntity.noContent().build();
    }


    // metodo para deshacer la eliminacion de un producto del carrito
    @PostMapping("/deshacer")
    // va a recibir  un objeto CarritoProducto en el cuerpo
    public ResponseEntity<Void> deshacerEliminacion(@RequestBody CarritoProducto carritoProducto) {
        // va a llamr al servicio para deshacer la eliminacion del producto
        carritoService.deshacerEliminacion(carritoProducto);
        // va a retorna una respuesta
        return ResponseEntity.noContent().build();
    }
}


package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Service.ProductoService;
import com.integradora.supermercadointegradora.Entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// se configura la ruta base para todas las solicitudes del controlador
@RequestMapping("/producto")
public class ProductoController {

    // servicio ProductoService para utilizar sus metodos
    @Autowired
    private ProductoService productoService;

    // metodo para agregar un nuevo producto
    @PostMapping("/agregarProducto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        // se llama  al servicio para agregar el nuevo producto
        Producto nuevoProducto = productoService.agregarProducto(producto);
        // va a retornar  la respuesta con el nuevo producto agregado
        return ResponseEntity.ok(nuevoProducto);
    }
}


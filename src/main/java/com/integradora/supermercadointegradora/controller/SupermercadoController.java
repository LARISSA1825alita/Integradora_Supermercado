package com.integradora.supermercadointegradora.controller;
import com.integradora.supermercadointegradora.Service.SupermercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supermercado")
public class SupermercadoController {

    @Autowired
    private SupermercadoService supermercadoService;

    // metodo  para procesar la compra de un cliente
    @PostMapping("/comprar/{clienteId}")
    //va a recibir el id del cliente como una variable de ruta
    public ResponseEntity<String> procesarCompra(@PathVariable Long clienteId) {
        // Llama al servicio para procesar la compra del cliente
        String respuesta = supermercadoService.procesarCompra(clienteId);
        // va a retornar la respuesta con un mensaje de éxito
        return ResponseEntity.ok(respuesta);
    }
}

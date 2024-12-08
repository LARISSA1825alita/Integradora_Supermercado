package com.integradora.supermercadointegradora.Service;

import org.springframework.stereotype.Service;

@Service
public class SupermercadoService {

    public String procesarCompra(Long clienteId) {
        // Aquí implementas la lógica de la compra.
        return "Compra procesada para el cliente con ID: " + clienteId;
    }
}



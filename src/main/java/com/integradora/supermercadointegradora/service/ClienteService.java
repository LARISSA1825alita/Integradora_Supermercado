package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}

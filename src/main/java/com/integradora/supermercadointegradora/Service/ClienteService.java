package com.integradora.supermercadointegradora.Service;
import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}

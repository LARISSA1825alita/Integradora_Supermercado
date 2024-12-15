package com.integradora.supermercadointegradora.response;

import com.integradora.supermercadointegradora.entity.Cliente;
import java.util.List;

public class ClienteResponse {
    private List<Cliente> clientes;
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setQueue(String string) {

    }
}
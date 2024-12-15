
package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.entity.Cliente;
import com.integradora.supermercadointegradora.response.ClienteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IClienteService {
    public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente);
    public ResponseEntity<ClienteResponseRest> agregarCaja(Long id);
    public ResponseEntity<ClienteResponseRest> atenderCaja();
    public ResponseEntity<ClienteResponseRest> consultarCaja();
    public ResponseEntity<ClienteResponseRest> buscarclientes();

}

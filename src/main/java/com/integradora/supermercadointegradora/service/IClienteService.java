
package org.example.estructuradedatos4f.service;

import org.example.estructuradedatos4f.entity.Cliente;
import org.example.estructuradedatos4f.response.CarritoProductoResponseRest;
import org.example.estructuradedatos4f.response.ClienteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IClienteService {
    public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente);
    public ResponseEntity<ClienteResponseRest> agregarCaja(Long id);
    public ResponseEntity<ClienteResponseRest> atenderCaja();
    public ResponseEntity<ClienteResponseRest> consultarCaja();
    public ResponseEntity<ClienteResponseRest> buscarclientes();

}

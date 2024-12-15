package com.integradora.supermercadointegradora.service;

import jakarta.annotation.PostConstruct;
import com.integradora.supermercadointegradora.entity.CarritoProducto;
import com.integradora.supermercadointegradora.entity.Cliente;
import com.integradora.supermercadointegradora.entity.dao.IClienteDAO;
import com.integradora.supermercadointegradora.entity.dao.IProductoDAO;
import com.integradora.supermercadointegradora.response.CarritoProductoResponseRest;
import com.integradora.supermercadointegradora.response.ClienteResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements com.integradora.supermercadointegradora.service.IClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private com.integradora.supermercadointegradora.service.Queue<Long> queue;

    @PostConstruct
    public void init() {
        queue = new Queue<>(100);
    }
    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente) {
        log.info("Inicio metodo crear cliente");

        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            if (cliente.getCarrito() != null) {
                for (CarritoProducto producto : cliente.getCarrito()) {
                    producto.setCliente(cliente);
                }
            }

            Cliente clienteGuardado = clienteDAO.save(cliente);

            if (clienteGuardado != null) {
                list.add(clienteGuardado);
                response.getClienteResponse().setClientes(list);
            } else {
                log.error("Error al guardar cliente");
                response.setMetada("Respuesta FALLIDA", "-1", "Cliente no guardado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error al guardar cliente", e);
            response.setMetada("Respuesta FALLIDA", "-1", "Error al guardar cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetada("Respuesta ok", "00", "Cliente creado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> agregarCaja(Long id) {
        log.info("Inicio metodo Agregar a la fila");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {

            Optional<Cliente> cliente = clienteDAO.findById(id);
            if(cliente.isPresent()) {
                list.add(cliente.get());
                queue.offer(cliente.get().getId());
                response.getClienteResponse().setClientes(list);
                log.info(queue.toString());
                response.setMetada("Respuesta OK", "00", "Respuesta exitosa, cliente agregado a la fila");
            }else {
                log.info("cliente no encontrado");
                response.setMetada("Respuesta No Encontrada", "-1", "cliente no encontrado");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al consultar por Id");
            log.error("Error al consultar por Id cliente: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> atenderCaja() {
        log.info("Inicio método atenderCaja");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();
        if (queue.isEmpty()) {
            log.warn("La fila está vacía");
            response.setMetada("fila vacía", "-1", "No hay clientes en la fila");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Long id = queue.poll();
        try {

            Optional<Cliente> cliente = clienteDAO.findById(id);
            if (cliente.isPresent()) {
                log.info("Cliente encontrado con ID: ", id);
                list.add(cliente.get());

                log.info("Cliente atendido, removido de la cola.");

                log.info(queue.toString());
                response.getClienteResponse().setClientes(list);
                response.setMetada("Respuesta OK", "00", "Respuesta exitosa, Cliente atendido");
            } else {
                log.warn("Cliente no encontrado con ID: {}", id);
                response.setMetada("Respuesta No Encontrada", "-1", "Cliente no encontrado");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al consultar por ID cliente: ", e);
            response.setMetada("Error", "-1", "Error al consultar por ID");
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Método atenderCaja finalizado con éxito.");
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponseRest> consultarCaja() {
        log.info("Iniciar metodo consultarCaja");

        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            // Verificar si la cola está vacía antes de continuar
            if (queue.isEmpty()) {
                log.info("No hay clientes en la caja");
                response.setMetada("No hay clientes en la caja", "-1", "La cola está vacía");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NO_CONTENT);
            }
            Queue<Long> respaldo = new Queue<>(100);
            Optional<Cliente> cliente;

            while (!queue.isEmpty()) {
                Long id = queue.poll();
                respaldo.offer(id); // Restauramos después de consultar
                cliente = clienteDAO.findById(id);
                cliente.ifPresent(list::add);
            }

            // Restaurar la cola original
            while (!respaldo.isEmpty()){
                queue.offer(respaldo.poll());
            }

            response.getClienteResponse().setClientes(list);
            response.setMetada("Respuesta OK", "00", "Clientes consultados correctamente");
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error al consultar la cola de clientes: ", e);
            response.setMetada("Error al consultar la cola", "-1", "Ocurrió un error inesperado al consultar la cola");
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ClienteResponseRest> buscarclientes() {
        log.info("Inicio metodo buscarclientes");
        ClienteResponseRest response = new ClienteResponseRest();
        try {
            List<Cliente> cliente = (List<Cliente>) clienteDAO.findAll();
            response.getClienteResponse().setClientes(cliente);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar la lista de clientes");
            log.error("Error al consultar clientes: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }





}
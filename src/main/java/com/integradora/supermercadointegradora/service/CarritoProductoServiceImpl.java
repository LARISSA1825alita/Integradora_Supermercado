package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.entity.Producto;
import com.integradora.supermercadointegradora.entity.Cliente;
import com.integradora.supermercadointegradora.entity.CarritoProducto;
import com.integradora.supermercadointegradora.entity.dao.IProductoDAO;
import com.integradora.supermercadointegradora.entity.dao.ICarritoProductoDAO;
import com.integradora.supermercadointegradora.entity.dao.IClienteDAO;
import com.integradora.supermercadointegradora.response.CarritoProductoResponseRest;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoProductoServiceImpl implements ICarritoProductoService {
    private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);

    private Pila<Long> pila;
    private Pila<Long> pilaCliente;
    private Pila<Long> pilaCantidad;

    @PostConstruct
    public void init() {
        pila = new Pila<>(100);
        pilaCliente = new Pila<>(100);
        pilaCantidad = new Pila<>(100);
    }

    @Autowired
    private ICarritoProductoDAO carritoProductoDAO;
    @Autowired
    private IClienteDAO clienteDAO;
    @Autowired
    private IProductoDAO productoDAO;

    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> agregarAlCarrito(CarritoProducto carritoProducto) {
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> listaProductos = new ArrayList<>();

        try {
            // Validaciones iniciales
            if (carritoProducto.getCliente() == null || carritoProducto.getProducto() == null) {
                response.setMetada("Error", "-1", "Cliente o Producto no pueden ser nulos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Verificar que el producto existe
            Optional<Producto> productoOpt = productoDAO.findById(carritoProducto.getProducto().getId());
            if (!productoOpt.isPresent()) {
                response.setMetada("Error", "-1", "Producto no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Verificar si el producto ya está en el carrito
            Optional<CarritoProducto> existente = carritoProductoDAO.findByClienteAndProducto(carritoProducto.getCliente(), carritoProducto.getProducto());
            if (existente.isPresent()) {
                log.debug("Producto ya existente en el carrito. Incrementando cantidad.");
                existente.get().setCantidad(existente.get().getCantidad() + carritoProducto.getCantidad());
                carritoProductoDAO.save(existente.get());
            } else {
                log.debug("Producto no está en el carrito. Agregándolo.");
                carritoProductoDAO.save(carritoProducto);
            }

            // Obtener la lista de productos actualizada
            listaProductos = carritoProductoDAO.findByCliente(carritoProducto.getCliente());
            response.getCarritoProductoResponse().setCarritoProductos(listaProductos);
            response.setMetada("Respuesta OK", "00", "Producto agregado exitosamente al carrito");

        } catch (DataIntegrityViolationException e) {
            log.error("Error de integridad de datos: ", e);
            response.setMetada("Error", "-1", "Violación de integridad de datos: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Error inesperado: ", e);
            response.setMetada("Error", "-1", "Error inesperado: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CarritoProductoResponseRest> consultarCarrito(Long id) {
        log.info("Consultando carrito por ID de cliente: " + id);
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> productosEnCarrito = new ArrayList<>();

        try {
            Optional<Cliente> cliente = clienteDAO.findById(id);

            if (cliente.isPresent()) {
                productosEnCarrito = carritoProductoDAO.findByCliente(cliente.get());

                if (!productosEnCarrito.isEmpty()) {
                    response.getCarritoProductoResponse().setProductos(productosEnCarrito);
                    response.setMetada("Respuesta OK", "00", "Productos encontrados en el carrito");
                } else {
                    log.info("El carrito del cliente no contiene productos");
                    response.setMetada("Respuesta vacía", "-1", "El carrito está vacío");
                    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                }
            } else {
                log.info("Cliente no encontrado");
                response.setMetada("Respuesta no encontrada", "-1", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al consultar el carrito", e);
            response.setMetada("Error", "-1", "Error interno al consultar el carrito");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> eliminarProducto(CarritoProducto carritoProducto) {
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> listaProductos;

        try {
            Optional<Producto> productoOpt = productoDAO.findById(carritoProducto.getProducto().getId());
            if (!productoOpt.isPresent()) {
                response.setMetada("Error", "-1", "Producto no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Optional<CarritoProducto> existente = carritoProductoDAO.findByClienteAndProducto(carritoProducto.getCliente(), carritoProducto.getProducto());
            if (existente.isPresent()) {
                CarritoProducto productoEnCarrito = existente.get();

                // Guardar estado previo
                CarritoProducto productoss = new CarritoProducto();
                productoss.setId(productoEnCarrito.getId());
                productoss.setProducto(productoEnCarrito.getProducto());
                productoss.setCantidad(productoEnCarrito.getCantidad());

                pilaCliente.push(carritoProducto.getCliente().getId());
                pila.push(productoss.getProducto().getId());
                pilaCantidad.push(carritoProducto.getCantidad());

                // Reducir cantidad o eliminar si llega a 0
                productoEnCarrito.setCantidad(productoEnCarrito.getCantidad() - carritoProducto.getCantidad());
                if (productoEnCarrito.getCantidad() <= 0) {
                    carritoProductoDAO.delete(productoEnCarrito);
                } else {
                    carritoProductoDAO.save(productoEnCarrito);
                }
            } else {
                response.setMetada("Error", "-1", "Producto no encontrado en el carrito");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            listaProductos = carritoProductoDAO.findByCliente(carritoProducto.getCliente());
            response.getCarritoProductoResponse().setCarritoProductos(listaProductos);
            response.setMetada("Respuesta OK", "00", "Producto eliminado exitosamente del carrito");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar producto del carrito");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> deshacerEliminarProducto() {
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> listaProductos;

        try {
            // Verificar si hay operaciones para deshacer
            if (pila.isEmpty() || pilaCliente.isEmpty() || pilaCantidad.isEmpty()) {
                response.setMetada("Error", "-1", "No hay operaciones para deshacer");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Recuperar la última operación
            Long clienteId = pilaCliente.pop();
            Long productoId = pila.pop();
            Long cantidad = pilaCantidad.pop();


            log.info("Cliente", clienteId);
            log.info("productoId", productoId);
            log.info("cantidad", cantidad);


            Optional<Producto> productoOpt = productoDAO.findById(productoId);


            Optional<Cliente> clienteOpt = clienteDAO.findById(clienteId);


            Producto producto = productoOpt.get();
            Cliente cliente = clienteOpt.get();

            // Verificar si el producto ya estaba en el carrito
            Optional<CarritoProducto> existenteCarrito = carritoProductoDAO.findByClienteAndProducto(cliente, producto);

            if (existenteCarrito.isPresent()) {
                // Incrementar cantidad del producto en el carrito
                CarritoProducto productoEnCarrito = existenteCarrito.get();
                productoEnCarrito.setCantidad(productoEnCarrito.getCantidad() + cantidad);
                carritoProductoDAO.save(productoEnCarrito);
            } else {
                // Reagregar el producto al carrito con la cantidad deshecha
                CarritoProducto nuevoCarritoProducto = new CarritoProducto();
                nuevoCarritoProducto.setCliente(cliente);
                nuevoCarritoProducto.setProducto(producto);
                nuevoCarritoProducto.setCantidad(cantidad);
                carritoProductoDAO.save(nuevoCarritoProducto);
            }

            // Obtener el carrito actualizado
            listaProductos = carritoProductoDAO.findByCliente(cliente);
            if (listaProductos.isEmpty()) {
                response.setMetada("Error", "-1", "El carrito está vacío después de deshacer");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }

            response.getCarritoProductoResponse().setCarritoProductos(listaProductos);
            response.setMetada("Respuesta OK", "00", "Operación deshecha exitosamente");

        } catch (Exception e) {
            log.error("Error al deshacer operación: ", e);
            response.setMetada("Error", "-1", "Error al deshacer operación: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //vaciara el carrito
    @Override
    @Transactional
    public ResponseEntity<CarritoProductoResponseRest> comprarCarrito(Long clienteId) {
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();

        try {
            // Verificar si el cliente existe
            Optional<Cliente> clienteOpt = clienteDAO.findById(clienteId);
            if (!clienteOpt.isPresent()) {
                response.setMetada("Error", "-1", "Cliente no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Obtener todos los productos del carrito del cliente
            Cliente cliente = clienteOpt.get();
            List<CarritoProducto> productosEnCarrito = carritoProductoDAO.findByCliente(cliente);

            if (productosEnCarrito.isEmpty()) {
                response.setMetada("Error", "-1", "El carrito ya está vacío");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }

            // Calcular el total del carrito
            double total = 0.0;
            for (CarritoProducto carritoProducto : productosEnCarrito) {
                total += carritoProducto.getProducto().getPrecio() * carritoProducto.getCantidad();  // Precio por cantidad
            }

            // Eliminar los productos del carrito
            carritoProductoDAO.deleteAll(productosEnCarrito);

            // Devolver la respuesta con el total
            response.setMetada("Respuesta OK", "00", "Carrito comprado exitosamente. El total del carrito sería de: " + total);
            response.getCarritoProductoResponse().setCarritoProductos(new ArrayList<>());  // El carrito está vacío después de la compra

        } catch (Exception e) {
            log.error("Error al vaciar el carrito: ", e);
            response.setMetada("Error", "-1", "Error interno al vaciar el carrito: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
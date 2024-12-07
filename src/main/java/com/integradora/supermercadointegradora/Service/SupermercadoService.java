package com.integradora.supermercadointegradora.Service;

import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Service
public class SupermercadoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Stack para registrar los productos eliminados del carrito
    private Stack<CarritoProducto> historialEliminados = new Stack<>();

    // se vaa procesar la compra de un cliente
    public String procesarCompra(Long clienteId) {
        // se obtendra el carrito de productos del cliente
        List<CarritoProducto> productosEnCarrito = carritoProductoRepository.findByClienteId(clienteId);

        if (productosEnCarrito.isEmpty()) {
            return "El carrito del cliente esta vacio";
        }
        // aqui se calculara el total de la compra
        double total = 0;
        for (CarritoProducto carritoProducto : productosEnCarrito) {
            Producto producto = carritoProducto.getProducto();
            total += producto.getPrecio() * carritoProducto.getCantidad();
        }

        // vaciar el carrito del cliente despues de la compra
        carritoProductoRepository.deleteAll(productosEnCarrito);

        return "Compra finalizada. Total: " + total;
    }

    public String agregarProductoAlCarrito(Long clienteId, Long productoId, int cantidad) {
        // Verificar si el cliente y el producto existen
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if (cliente == null) {
            return "Cliente no encontrado";
        }

        if (producto == null) {
            return "Producto no encontrado";
        }

        // Verificar si el producto ya está en el carrito
        Optional<CarritoProducto> carritoProductoOpt = carritoProductoRepository.findByClienteAndProducto(cliente, producto);
        if (carritoProductoOpt.isPresent()) {
            CarritoProducto carritoProducto = carritoProductoOpt.get();
            // Si el producto ya está en el carrito, actualizar la cantidad
            carritoProducto.setCantidad(carritoProducto.getCantidad() + cantidad);
            carritoProductoRepository.save(carritoProducto);
        } else {
            // Si el producto no está en el carrito, agregarlo
            CarritoProducto carritoProducto = new CarritoProducto();
            carritoProducto.setCliente(cliente);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad(cantidad);
            carritoProductoRepository.save(carritoProducto);
        }

        return "El producto se agrego al carrito";
    }

// se elimina un producto del carrito de un cliente
    public String eliminarProductoDelCarrito(Long clienteId, Long productoId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if (cliente == null) {
            return "No se encontro al cliente";
        }

        if (producto == null) {
            return "No se encontro el producto";
        }

        Optional<CarritoProducto> carritoProductoOpt = carritoProductoRepository.findByClienteAndProducto(cliente, producto);
        if (carritoProductoOpt.isPresent()) {
            CarritoProducto carritoProducto = carritoProductoOpt.get();
            // Registrar el producto eliminado en el historial
            historialEliminados.push(carritoProducto);
            carritoProductoRepository.delete(carritoProducto);
            return "Se elimino el producto del carrirto";
        } else {
            return "El producto no se encuentra en el carrito";
  }
 }

    // Deshacer la ultima eliminacion de un producto del carrito
    public String deshacerUltimaEliminacion(Long clienteId) {
        if (historialEliminados.isEmpty()) {
            return "No hay productos para eliminar";
        }

        CarritoProducto productoEliminado = historialEliminados.pop();
        Cliente cliente = productoEliminado.getCliente();

        if (!cliente.getId().equals(clienteId)) {
            return "El producto eliminado nno coincide con el cliente";
        }

        // Volver a agregar el producto al carrito
        carritoProductoRepository.save(productoEliminado);
        return "ltima eliminación deshecha, producto agregado nuevamente al carrito.";
    }
    // Obtener todos los productos en el carrito de un cliente
    public List<CarritoProducto> obtenerCarrito(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            return null;
        }
        return carritoProductoRepository.findByClienteId(clienteId);
 }

    // Obtener el total de la compra de un cliente
    public double obtenerTotalCarrito(Long clienteId) {
        List<CarritoProducto> productosEnCarrito = carritoProductoRepository.findByClienteId(clienteId);
        double total = 0;
        for (CarritoProducto carritoProducto : productosEnCarrito) {
            Producto producto = carritoProducto.getProducto();
            total += producto.getPrecio() * carritoProducto.getCantidad();
        }
        return total;
    }

}


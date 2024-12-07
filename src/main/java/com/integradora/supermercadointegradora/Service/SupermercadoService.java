package com.integradora.supermercadointegradora.Service;

import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import com.integradora.supermercadointegradora.repository.ClienteRepository;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // Aqui agregas lo que sigue ali

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

        return "Compra finalizada. Total: "+total;
     }
    }

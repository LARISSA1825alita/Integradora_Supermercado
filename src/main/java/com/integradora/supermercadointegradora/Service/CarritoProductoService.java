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
public class CarritoProductoService {

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private Stack<CarritoProducto> historialEliminados = new Stack<>();

    //metodo que agregara un producto al carrito
    public CarritoProducto agregarProductoAlCarrito(Long clienteId, Long productoId, int cantidad) {
        // se va a buscar al cliente con su id
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        //se va a buscar el producto cin su id
        Optional<Producto> productoOpt = productoRepository.findById(productoId);
        //se checara que el cliente y producto existan
        if (clienteOpt.isPresent() && productoOpt.isPresent()) {
            // Se obtiene el cliente y el producto de los Optional con get
            Cliente cliente = clienteOpt.get();
            Producto producto = productoOpt.get();

            //instancia para la relacion cliente-produ-cant
            CarritoProducto carritoProducto = new CarritoProducto();
            carritoProducto.setCliente(cliente);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad(cantidad);

            //se va a guardar el ob CarritoProducto
            return carritoProductoRepository.save(carritoProducto);
        }

        return null;
}
    // se obtendran los productos en el carrito de un cliente
    public List<CarritoProducto> obtenerProductosEnCarrito(Long clienteId) {
        // llama al repositorio de carritoProducto para obtener todos los productos
        // asociados al cliente cuyo id es el que se pasa como parametro.
        return carritoProductoRepository.findByClienteId(clienteId);
    }

    // Eliminar un producto del carrito
    public boolean eliminarProductoDelCarrito(Long carritoProductoId) {
        // Se busca el producto en el carrito usando su id
        Optional<CarritoProducto> carritoProductoOpt = carritoProductoRepository.findById(carritoProductoId);

        // Si el producto existe en la base de datos
        if (carritoProductoOpt.isPresent()) {
            // Se obtiene el objeto CarritoProducto del contenedor Optional
            CarritoProducto carritoProducto = carritoProductoOpt.get();

            // Se elimina el producto del carrito mediante
            carritoProductoRepository.delete(carritoProducto);

            // Se agrega el producto eliminado a una pila para que se pueda recuperar
            historialEliminados.push(carritoProducto); // Agregar a la pila de eliminados

            // Se retorna verdadero, indicando que el producto se elimino
            return true;
        }

        // Si el producto no se encuentra se retorna falso
        return false;
    }

    // se va a deshacer la utima eliminacion de un producto
    public CarritoProducto deshacerUltimaEliminacion() {
        if (!historialEliminados.isEmpty()) {
            CarritoProducto carritoProducto = historialEliminados.pop();
            carritoProductoRepository.save(carritoProducto);
            return carritoProducto;
        }

        return null;
}
}
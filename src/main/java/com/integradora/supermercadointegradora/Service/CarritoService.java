package com.integradora.supermercadointegradora.Service;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarritoService {
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    // metodo para agregar un producto al carrito
    // va a recibir un objeto CarritoProducto y lo guarda en la bd
    public CarritoProducto agregarProducto(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }
    //metodo para obtener los productos del carrito de un cliente específico
    // va a recibir el id del cliente
    public List<CarritoProducto> obtenerCarrito(Long clienteId) {
        return carritoProductoRepository.findByClienteId(clienteId);
    }
    //metodo para eliminar un producto del carrito
    // va a recibir  un objeto CarritoProducto y lo elimina de la bd
    public void eliminarProducto(CarritoProducto carritoProducto) {
        carritoProductoRepository.delete(carritoProducto);
    }
    //metodo para deshacer la eliminación de un producto
    //guardara el objeto CarritoProducto en la bd
    public void deshacerEliminacion(CarritoProducto carritoProducto) {
        carritoProductoRepository.save(carritoProducto);
    }
}

package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.Custom.CustomStack;
import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.repository.CarritoProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CarritoService {
    private final CarritoProductoRepository carritoProductoRepository;
    private final CustomStack<CarritoProducto> historialEliminados = new CustomStack<>();
//este constructor va a inyectar en el carrito
    public CarritoService(CarritoProductoRepository carritoProductoRepository) {
        this.carritoProductoRepository = carritoProductoRepository;
    }
//este metodo va a agregar un producto al carrito
    public CarritoProducto agregarProducto(CarritoProducto carritoProducto) {
        //va a retornar el producto que se guardo
        return carritoProductoRepository.save(carritoProducto);
    }
//metodo para la lista de los productos
    public List<CarritoProducto> listarCarrito(Long clienteId) {
        //se obtienen los productos del carrito
        return carritoProductoRepository.findAll().stream()
                .filter(cp -> cp.getCliente().getId().equals(clienteId))
                .collect(Collectors.toList());
    }
//este metodo va a eliminar uno de los productos del carrito
    public void eliminarProducto(Long id) {
        CarritoProducto eliminado = carritoProductoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el producto con el id: " + id));
        // se elimina el producto de la base de datos
        carritoProductoRepository.delete(eliminado);
        //se guarda el producto que ya se elimino en la pila
        historialEliminados.push(eliminado);
    }
//este metodo sera para deshacer lo eliminado
    public void deshacerEliminacion() {
        //va a checar si la pila de productos no esta vacia
        if (!historialEliminados.isEmpty()) {
            //se vaa recuerar el ultimo producto que se elimino de la pila
            CarritoProducto restaurado = historialEliminados.pop();
            //aqui se va a guardar el producto que se volvio a restaurar
            carritoProductoRepository.save(restaurado);
        }
    }
}

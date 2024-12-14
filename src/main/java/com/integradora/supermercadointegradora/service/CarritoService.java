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

    public CarritoService(CarritoProductoRepository carritoProductoRepository) {
        this.carritoProductoRepository = carritoProductoRepository;
    }

    public CarritoProducto agregarProducto(CarritoProducto carritoProducto) {
        return carritoProductoRepository.save(carritoProducto);
    }

    public List<CarritoProducto> listarCarrito(Long clienteId) {
        return carritoProductoRepository.findAll().stream()
                .filter(cp -> cp.getCliente().getId().equals(clienteId))
                .collect(Collectors.toList());
    }

    public void eliminarProducto(Long id) {
        CarritoProducto eliminado = carritoProductoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + id));
        carritoProductoRepository.delete(eliminado);
        historialEliminados.push(eliminado);
    }

    public void deshacerEliminacion() {
        if (!historialEliminados.isEmpty()) {
            CarritoProducto restaurado = historialEliminados.pop();
            carritoProductoRepository.save(restaurado);
        }
    }
}

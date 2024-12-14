package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
}

package com.integradora.supermercadointegradora.Service;

import com.integradora.supermercadointegradora.repository.ProductoRepository;
import com.integradora.supermercadointegradora.Entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
}

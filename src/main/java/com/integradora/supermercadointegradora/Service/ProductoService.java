package com.integradora.supermercadointegradora.Service;

import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Agregar un nuevo producto
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
 }
}
package com.integradora.supermercadointegradora.Service;

import com.integradora.supermercadointegradora.Entity.Producto;
import com.integradora.supermercadointegradora.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // este metodo va a guardar el producto en la base de datos
    public Producto agregarProducto(Producto producto) {
        try {
            // se va guarda  el producto en la base de datos
            return productoRepository.save(producto);
        } catch (Exception e) {
            // las execpciones para los errores
            throw new RuntimeException("Error al agregar el producto", e);
        }
    }
}

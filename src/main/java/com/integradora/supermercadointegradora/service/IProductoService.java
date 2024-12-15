package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.entity.Producto;
import com.integradora.supermercadointegradora.response.ProductoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductoService {
    public ResponseEntity<ProductoResponseRest> crear(Producto producto);
    public ResponseEntity<ProductoResponseRest> buscarproducto();

}

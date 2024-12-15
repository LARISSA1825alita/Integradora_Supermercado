package com.integradora.supermercadointegradora.service;

import com.integradora.supermercadointegradora.entity.CarritoProducto;
import com.integradora.supermercadointegradora.response.CarritoProductoResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICarritoProductoService {
    public ResponseEntity<CarritoProductoResponseRest> agregarAlCarrito(CarritoProducto carritoProducto);
    public ResponseEntity<CarritoProductoResponseRest> consultarCarrito(Long id);
    public ResponseEntity<CarritoProductoResponseRest> eliminarProducto(CarritoProducto carritoProducto);
    public ResponseEntity<CarritoProductoResponseRest> deshacerEliminarProducto();

    public ResponseEntity<CarritoProductoResponseRest> comprarCarrito(Long id);
}

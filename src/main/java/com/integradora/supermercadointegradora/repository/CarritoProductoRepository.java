package com.integradora.supermercadointegradora.repository;

import com.integradora.supermercadointegradora.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByCliente(Cliente cliente);
    CarritoProducto findByClienteAndProducto(Cliente cliente, Producto producto);
}

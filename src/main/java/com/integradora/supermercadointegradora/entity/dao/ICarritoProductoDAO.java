package com.integradora.supermercadointegradora.entity.dao;
import com.integradora.supermercadointegradora.entity.CarritoProducto;
import com.integradora.supermercadointegradora.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import com.integradora.supermercadointegradora.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICarritoProductoDAO extends CrudRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByCliente(Cliente cliente);
    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.cliente.id = :clienteId")
    List<CarritoProducto> findProductosByClienteId(@Param("clienteId") Long clienteId);
    Optional<CarritoProducto> findByClienteAndProducto(Cliente cliente, Producto producto);

    Optional<CarritoProducto> findByProducto(Producto producto);
}


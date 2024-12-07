package com.integradora.supermercadointegradora.repository;

import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import com.integradora.supermercadointegradora.Entity.Cliente;
import com.integradora.supermercadointegradora.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByClienteId(Long clienteId);
    Optional<CarritoProducto> findByClienteAndProducto(Cliente cliente, Producto producto);
}
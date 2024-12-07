package com.integradora.supermercadointegradora.repository;

import com.integradora.supermercadointegradora.Entity.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByClienteId(Long clienteId);
}
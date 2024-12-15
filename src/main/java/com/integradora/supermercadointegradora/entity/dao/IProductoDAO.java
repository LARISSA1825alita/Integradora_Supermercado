package com.integradora.supermercadointegradora.entity.dao;

import com.integradora.supermercadointegradora.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProductoDAO extends CrudRepository<Producto, Integer> {
    Optional<Producto> findById(Long id);
}

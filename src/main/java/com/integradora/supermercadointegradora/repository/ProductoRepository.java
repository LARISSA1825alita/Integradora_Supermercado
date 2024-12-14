package com.integradora.supermercadointegradora.repository;


import com.integradora.supermercadointegradora.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
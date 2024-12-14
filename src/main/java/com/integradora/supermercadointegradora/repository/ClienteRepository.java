package com.integradora.supermercadointegradora.repository;


import com.integradora.supermercadointegradora.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
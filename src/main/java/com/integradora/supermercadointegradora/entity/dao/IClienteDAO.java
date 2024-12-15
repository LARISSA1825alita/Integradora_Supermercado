package com.integradora.supermercadointegradora.entity.dao;

import org.example.estructuradedatos4f.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDAO  extends CrudRepository<Cliente, Long> {
}

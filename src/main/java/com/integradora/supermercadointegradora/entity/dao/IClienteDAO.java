package com.integradora.supermercadointegradora.entity.dao;

import com.integradora.supermercadointegradora.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDAO  extends CrudRepository<Cliente, Long> {
}

package com.mx.Clientes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Clientes.Entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
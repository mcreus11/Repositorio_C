package com.mx.Cliente.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Cliente.Entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	public List<Cliente> findByTiendaId(int tiendaId);
	boolean existsByNombreAndAppIgnoringCase(String nombre, String app);
	boolean existsByNombreAndAppIgnoringCaseAndIdClienteNot(String nombre, String app, int idClienteActual);


}

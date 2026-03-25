package com.mx.Departamento.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Departamento.Entity.Departamento;

@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Integer>{

	public List<Departamento> findByTiendaId(int tiendaId);
	public boolean existsByNombreAllIgnoringCase(String nombre);
}

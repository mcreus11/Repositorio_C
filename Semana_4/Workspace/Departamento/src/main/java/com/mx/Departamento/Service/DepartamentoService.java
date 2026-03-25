package com.mx.Departamento.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Departamento.Entity.Departamento;
import com.mx.Departamento.Repository.DepartamentoRepo;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepo dao;

	public List<Departamento> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	public void guardar(Departamento departamento) {
		dao.save(departamento);
	}

	public boolean existeDepartamento(String nombre) {
		return dao.existsByNombreAllIgnoringCase(nombre);
	}

	public void editar(Departamento departamento) {
		dao.save(departamento);
	}

	public Departamento buscar(int idDepartamento) {
		return dao.findById(idDepartamento).orElse(null);
	}

	public void eliminar(int idDepartamento) {
		dao.deleteById(idDepartamento);
	}

	public List<Departamento> listarPorDepa(int tiendaId){
		return dao.findByTiendaId(tiendaId);
	}

	
}

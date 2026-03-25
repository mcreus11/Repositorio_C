package com.mx.Empleado.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Empleado.Entity.Empleado;
import com.mx.Empleado.Repository.EmpleadoRepo;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepo dao;

	
	public List<Empleado> listar() {
		return dao.findAll();
	}

	public Empleado buscar(int id) {
		return dao.findById(id).orElse(null);
	}

	public List<Empleado> listarPorTienda(int tiendaId) {
		return dao.findByTiendaId(tiendaId);
	}


	public Empleado guardar(Empleado empleado) {
		
		Empleado existePorNombreApp = dao.findByNombreAndApp(empleado.getNombre(), empleado.getApp());
		if (existePorNombreApp != null && existePorNombreApp.getIdEmpleado() != empleado.getIdEmpleado()) {
			return null; 
		}

		Empleado existePorContacto = dao.findByContacto(empleado.getContacto());
		if (existePorContacto != null && existePorContacto.getIdEmpleado() != empleado.getIdEmpleado()) {
			return null; 
		}
		
		return dao.save(empleado);
	}
	
	public void eliminar(int id) {
		dao.deleteById(id);
	}
	
public Empleado editar(Empleado empleado) {
		
		Empleado existePorNombreApp = dao.findByNombreAndApp(empleado.getNombre(), empleado.getApp());
		if (existePorNombreApp != null && existePorNombreApp.getIdEmpleado() != empleado.getIdEmpleado()) {
			return null; 
		}

		Empleado existePorContacto = dao.findByContacto(empleado.getContacto());
		if (existePorContacto != null && existePorContacto.getIdEmpleado() != empleado.getIdEmpleado()) {
			return null; 
		}
		
		return dao.save(empleado);
	}

}


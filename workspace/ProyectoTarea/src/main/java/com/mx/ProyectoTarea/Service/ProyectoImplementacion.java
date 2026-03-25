package com.mx.ProyectoTarea.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.ProyectoTarea.Dao.IProyectoDao;
import com.mx.ProyectoTarea.Dominio.Proyecto;

@Service
public class ProyectoImplementacion implements IProyectoService{

	@Autowired
	private IProyectoDao dao;
	@Override
	public List<Proyecto> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public void guardar(Proyecto proyecto) {
		dao.save(proyecto);
	}

	@Override
	public void editar(Proyecto proyecto) {
		dao.save(proyecto);
	}

	@Override
	public Proyecto buscar(int idProyecto) {
		return dao.findById(idProyecto).orElse(null);
	}

	@Override
	public void eliminar(int idProyecto) {
		dao.deleteById(idProyecto);
	}


	@Override
	public List<Proyecto> findProyectosVencidos() {
		return dao.findProyectosVencidos();
	}

	@Override
	public List<Proyecto> findProyectosPorNombre(String nombre) {
		return dao.findByNombreContainingIgnoreCase(nombre);
	}

}

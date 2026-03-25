package com.mx.ProyectoTarea.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.ProyectoTarea.Dao.ITareaDao;
import com.mx.ProyectoTarea.Dominio.Tarea;

@Service
public class TareaImplementacion implements ITareaService{

	@Autowired
	private ITareaDao dao; 
	@Autowired

	@Override
	public List<Tarea> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public void guardar(Tarea tarea) {
		dao.save(tarea);
	}

	@Override
	public void editar(Tarea tarea) {
		dao.save(tarea);
	}

	@Override
	public Tarea buscar(int idTarea) {
		return dao.findById(idTarea).orElse(null);
	}

	@Override
	public void eliminar(int idTarea) {
		dao.deleteById(idTarea);
	}


	@Override
	public List<Tarea> findTareasVencidas() {
		return dao.findTareasVencidas();
	}

}

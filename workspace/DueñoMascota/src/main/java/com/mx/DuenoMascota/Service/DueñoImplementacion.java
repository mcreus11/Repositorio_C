package com.mx.DuenoMascota.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.DuenoMascota.Dao.IDueñoDao;
import com.mx.DuenoMascota.Dominio.Dueño;
@Service
public class DueñoImplementacion implements IDueñoService{

	@Autowired 
	private IDueñoDao dao;
	@Override
	public List<Dueño> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	@Override
	public void guardar(Dueño dueño) {
		dao.save(dueño);
		
	}

	@Override
	public void editar(Dueño dueño) {
		dao.save(dueño);
		
	}

	@Override
	public Dueño buscar(int idDueño) {
		return dao.findById(idDueño).orElse(null);
	}

	@Override
	public void eliminar(int idDueño) {
		dao.deleteById(idDueño);
	}
	
	public boolean existeLibro(String nombre) {
	    return dao.existsByNombre(nombre);
	}
	

}

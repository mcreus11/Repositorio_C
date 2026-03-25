package com.mx.LibroPrestamo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.LibroPrestamo.Dao.ILibroDao;
import com.mx.LibroPrestamo.Dominio.Libro;

@Service
public class LibroImplementacion implements ILibroService{

	@Autowired
	private ILibroDao dao;
	@Override
	public List<Libro> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	}

	@Override
	public void guardar(Libro libro) {
		dao.save(libro);
		
	}

	@Override
	public void editar(Libro libro) {
		dao.save(libro);
		
	}

	@Override
	public Libro buscar(int idLibros) {
		return dao.findById(idLibros).orElse(null);
	}

	@Override
	public void eliminar(int idLibros) {
		dao.deleteById(idLibros);
		
	}
	
	public boolean existeLibro(String nombre) {
	    return dao.existsByNombre(nombre);
	}
	

}

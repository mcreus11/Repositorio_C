package com.mx.LibroPrestamo.Service;

import java.util.List;

import com.mx.LibroPrestamo.Dominio.Libro;

public interface ILibroService {
	public List<Libro> listar();
	public void guardar(Libro libro);
	public void editar(Libro libro);
	public Libro buscar(int idLibro); 
	public void eliminar(int idLibro); 

}

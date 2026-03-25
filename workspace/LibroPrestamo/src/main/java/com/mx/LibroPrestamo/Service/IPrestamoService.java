package com.mx.LibroPrestamo.Service;

import java.util.List;

import com.mx.LibroPrestamo.Dominio.Prestamo;

public interface IPrestamoService {
	public List<Prestamo> listar();
	public void guardar(Prestamo prestamo);
	public void editar(Prestamo prestamo);
	public Prestamo buscar(int idPrestamo);
	public void eliminar(int idPrestamo);
    List<Prestamo> obtenerPrestamosActivos();
    

}

package com.mx.LibroPrestamo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.LibroPrestamo.Dao.IPrestamoDao;
import com.mx.LibroPrestamo.Dominio.Prestamo;

@Service
public class PrestamoImplementacion implements IPrestamoService{

	@Autowired
	private IPrestamoDao dao;
	@Override
	public List<Prestamo> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idPrestamo"));
	}

	@Override
	public void guardar(Prestamo prestamo) {
	    dao.save(prestamo);
	}

	@Override
	public void editar(Prestamo prestamo) {
	    dao.save(prestamo);
	}

	@Override
	public Prestamo buscar(int idPrestamo) {
		return dao.findById(idPrestamo).orElse(null);
	}

	@Override
	public void eliminar(int idPrestamo) {
		dao.deleteById(idPrestamo);
		
	}
	
	@Override
    public List<Prestamo> obtenerPrestamosActivos() {
        // Se obtiene la fecha actual del sistema para usarla como parámetro en la consulta
        LocalDate fechaActual = LocalDate.now(); 
        
        // Llama al método del DAO que utiliza la consulta JPQL corregida:
        // @Query("SELECT p FROM Prestamo p WHERE p.fechaFin IS NULL OR p.fechaFin > :fechaActual")
        return dao.findactiveloans(fechaActual);
    }
	
	//metodo personalizado para editar la fecha fin
	public boolean actualizarFechaFin(int idPrestamo, LocalDate fechaFin) {
	    int filas = dao.actualizarFechaFin(idPrestamo, fechaFin);
	    return filas > 0; //retornara si se actualizo por lo menos una fila.
	}

}

package com.mx.LibroPrestamo.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.LibroPrestamo.Dominio.Prestamo;

import jakarta.transaction.Transactional;

@Repository
public interface IPrestamoDao extends JpaRepository<Prestamo, Integer>{
	
	// consultar los prestamos activos o que no tengan fecha de entrega
	@Query("SELECT p FROM Prestamo p WHERE p.fechaFin IS NULL OR p.fechaFin > :fechaActual")
	List<Prestamo> findactiveloans(@Param("fechaActual") LocalDate fechaActual);
	
	//metodo que me edite la fecha de fin
	@Modifying //indica que es una operacion de alteracion
	@Transactional //provve un conexto de transaccion necesaria
	@Query("UPDATE Prestamo p SET p.fechaFin = :fechaFin WHERE p.idPrestamo = :idPrestamo")
	int actualizarFechaFin(@Param("idPrestamo") int idPrestamo, @Param("fechaFin") LocalDate fechaFin);
}

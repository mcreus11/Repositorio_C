package com.mx.ProyectoTarea.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.ProyectoTarea.Dominio.Proyecto;

@Repository
public interface IProyectoDao extends JpaRepository<Proyecto, Integer>{
	
	@Query("SELECT p FROM Proyecto p WHERE p.fechaFin < CURRENT_DATE() AND p.estado != 'Terminado'")
	public List<Proyecto> findProyectosVencidos();
	
	List<Proyecto> findByNombreContainingIgnoreCase(String nombre);

}

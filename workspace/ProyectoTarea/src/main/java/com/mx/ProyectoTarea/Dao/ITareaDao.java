package com.mx.ProyectoTarea.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.ProyectoTarea.Dominio.Tarea;

@Repository
public interface ITareaDao extends JpaRepository<Tarea, Integer>{
	
	@Query("SELECT t FROM Tarea t WHERE t.estado != 'Completado' AND t.fechaVencimiento < CURRENT_DATE()")
    public List<Tarea> findTareasVencidas();

}

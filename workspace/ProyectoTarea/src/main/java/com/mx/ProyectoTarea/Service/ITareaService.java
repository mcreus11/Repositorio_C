package com.mx.ProyectoTarea.Service;

import java.util.List;

import com.mx.ProyectoTarea.Dominio.Tarea;


public interface ITareaService {
	public List<Tarea> listar();
    public void guardar(Tarea tarea); 
    public void editar(Tarea tarea); 
    public Tarea buscar(int idTarea); 
    public void eliminar(int idTarea); 

    public List<Tarea> findTareasVencidas();

}

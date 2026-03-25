package com.mx.ProyectoTarea.Service;

import java.util.List;

import com.mx.ProyectoTarea.Dominio.Proyecto;

public interface IProyectoService {
	public List<Proyecto> listar();
    public void guardar(Proyecto proyecto); 
    public void editar(Proyecto proyecto); 
    public Proyecto buscar(int idProyecto); 
    public void eliminar(int idProyecto); 

    public List<Proyecto> findProyectosVencidos();
    public List<Proyecto> findProyectosPorNombre(String nombre);

}

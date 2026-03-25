package com.mx.DuenoMascota.Service;

import java.util.List;

import com.mx.DuenoMascota.Dominio.Dueño;

public interface IDueñoService {
	public List<Dueño> listar();
    public void guardar(Dueño dueño);
    public void editar(Dueño dueño);
    public Dueño buscar(int idDueño);
    public void eliminar(int idDueño);

}

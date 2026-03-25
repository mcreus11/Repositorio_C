package com.mx.DuenoMascota.Service;

import java.util.List;

import com.mx.DuenoMascota.Dominio.Mascota;

public interface IMascotaService {

	public List<Mascota> listar();
    public void guardar(Mascota mascota);
    public void editar(Mascota mascota);
    public Mascota buscar(int idMascota);
    public void eliminar(int idMascota);
    List<Mascota> listarPorEspecie(String especie);
}

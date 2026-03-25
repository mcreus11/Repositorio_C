package com.mx.DuenoMascota.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.DuenoMascota.Dao.IMascotaDao;
import com.mx.DuenoMascota.Dominio.Mascota;

@Service
public class MascotaImplementacion implements IMascotaService{

	@Autowired
	private IMascotaDao dao;

	@Override
	public List<Mascota> listar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idMascota"));
	}

	@Override
	public void guardar(Mascota mascota) {
		dao.save(mascota);
		
	}

	@Override
	public void editar(Mascota mascota) {
		dao.save(mascota);
		
	}

	@Override
	public Mascota buscar(int idMascota) {
		return dao.findById(idMascota).orElse(null);
	}

	@Override
	public void eliminar(int idMascota) {
		dao.deleteById(idMascota);
		
	}

	@Override
	public List<Mascota> listarPorEspecie(String especie) {
		return dao.findMascotasByEspecie(especie);
	}
	

}

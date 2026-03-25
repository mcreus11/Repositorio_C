package com.mx.DuenoMascota.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.DuenoMascota.Dominio.Mascota;

@Repository
public interface IMascotaDao extends JpaRepository<Mascota, Integer> {
	
	@Query("SELECT p FROM Mascota p WHERE p.especie = :nombreEspecie")
    List<Mascota> findMascotasByEspecie(@Param("nombreEspecie") String especie);
}
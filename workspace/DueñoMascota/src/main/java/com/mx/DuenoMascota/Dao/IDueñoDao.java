package com.mx.DuenoMascota.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.DuenoMascota.Dominio.Dueño;

public interface IDueñoDao extends JpaRepository<Dueño, Integer> {
	 public boolean existsByNombre(String nombre);
}
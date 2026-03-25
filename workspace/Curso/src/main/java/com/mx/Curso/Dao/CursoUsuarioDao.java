package com.mx.Curso.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.Curso.Dominio.CursoUsuario; // Importación de la Entidad/Dominio

public interface CursoUsuarioDao extends JpaRepository<CursoUsuario, Integer> { 

    Optional<CursoUsuario> findByNombreAndAppAndApm(String nombre, String app, String apm);
}

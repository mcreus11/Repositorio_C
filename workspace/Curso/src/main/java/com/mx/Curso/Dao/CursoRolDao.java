package com.mx.Curso.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Curso.Dominio.CursoRol;

public interface CursoRolDao extends JpaRepository<CursoRol, Integer> {

    Optional<CursoRolDao> findByPrivilegio(String privilegio);
    
}
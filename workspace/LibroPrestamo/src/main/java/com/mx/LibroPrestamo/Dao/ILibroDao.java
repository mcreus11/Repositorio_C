package com.mx.LibroPrestamo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.LibroPrestamo.Dominio.Libro;



@Repository
public interface ILibroDao extends JpaRepository<Libro, Integer> {

    public boolean existsByNombre(String nombre);

}

package com.mx.Computadora.Dao;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.mx.Computadora.Dominio.Computadora;

@Repository
public interface IComputadoraDao extends JpaRepository<Computadora, Integer> {

    public Computadora findByMarca(String marca); 
}
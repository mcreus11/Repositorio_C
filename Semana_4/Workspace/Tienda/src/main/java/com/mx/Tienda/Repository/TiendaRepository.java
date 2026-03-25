package com.mx.Tienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Tienda.Entity.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer>{

}

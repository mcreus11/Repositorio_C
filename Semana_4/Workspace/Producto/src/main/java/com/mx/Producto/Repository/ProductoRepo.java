package com.mx.Producto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Producto.Entity.Producto;


@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer>{
	public List<Producto> findByTiendaId(int tiendaId);

}

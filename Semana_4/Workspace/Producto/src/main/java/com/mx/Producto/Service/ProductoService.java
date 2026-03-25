package com.mx.Producto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Producto.Entity.Producto;
import com.mx.Producto.Repository.ProductoRepo;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepo dao;
	
	public List<Producto> listar(){
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idProducto"));
	}
	
	public void guardar(Producto producto) {
		dao.save(producto);
	}
	
	public void editar(Producto producto) {
		dao.save(producto);
	}
	
	public Producto buscar(int idProducto) {
		return dao.findById(idProducto).orElse(null);
	}

	
	public void eliminar (int idProducto) {
		dao.deleteById(idProducto);
	}
	
	public List<Producto> listarPorTienda(int tiendaId){
		return dao.findByTiendaId(tiendaId);
	}
}

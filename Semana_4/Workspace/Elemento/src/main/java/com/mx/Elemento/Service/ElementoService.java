package com.mx.Elemento.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Elemento.Entity.Elemento;
import com.mx.Elemento.Repository.ElementoRepo;

@Service
public class ElementoService{

	@Autowired 
	private ElementoRepo dao;
	
	
	
	public List<Elemento> listar(){
		return dao.findAll();
	}
	
	public Elemento guardar(Elemento elemento) {
		return dao.save(elemento);
	}
	
	
}

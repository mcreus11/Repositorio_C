package com.mx.Elemento.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Elemento.Entity.Elemento;
import com.mx.Elemento.Service.ElementoService;

@RestController
@RequestMapping("/elementos")
@CrossOrigin
public class ElementoController {

	@Autowired
	private ElementoService service;
	
	
	@GetMapping
	public List<Elemento> listar(){
		return service.listar();
	}
	
	@PostMapping
	public Elemento guardar (@RequestBody Elemento elemento) {
		return service.guardar(elemento);
	}
}

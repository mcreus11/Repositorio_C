package com.mx.Departamento.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Departamento.Entity.Departamento;
import com.mx.Departamento.Service.DepartamentoService;

@RestController
@RequestMapping("/Departamento")
//@CrossOrigin
public class DepartamentoController {
	
	@Autowired
	DepartamentoService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
	    List<Departamento> depas = service.listar();
	    if(depas.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }else {
	        return ResponseEntity.ok(depas);
	    }
	}

	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Departamento departamento){
	    boolean existente = service.existeDepartamento(departamento.getNombre());
	    if(existente) {
	    	return ResponseEntity.status(HttpStatus.CONFLICT)
	    			.body("Este departamento ya esta registrado");
	    }else {
	    	service.guardar(departamento);
	    	return ResponseEntity.status(HttpStatus.CREATED)
	    			.body("El departamento " + departamento.getNombre() + " se registro");
	    }
	}
	
	@PutMapping
	public ResponseEntity<?> editar(@RequestBody Departamento departamento){
	    Departamento depa = service.buscar(departamento.getIdDepartamento());
	    if(depa != null) {
	        service.editar(departamento);
	        return ResponseEntity.accepted().body("Edicion exitosa");
	    }else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Error, el registro que intentas editar no existe");
	    }
	}
	
	@DeleteMapping("/{idDepartamento}")
	public ResponseEntity<?> eliminar(@PathVariable int idDepartamento){
	    Departamento depa = service.buscar(idDepartamento);
	    if(depa == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Error, el registro que intenta eliminar no existe");
	    }else {
	        service.eliminar(idDepartamento);
	        return ResponseEntity.ok("Eliminacion exitosa");
	    }
	}
	
	@GetMapping("/{idDepartamento}")
	public ResponseEntity<?> buscar(@PathVariable int idDepartamento){
	    Departamento depa = service.buscar(idDepartamento);
	    if(depa == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("El registro no existe");
	    }else {
	        return ResponseEntity.ok(depa);
	    }
	}

	@GetMapping("porTiendas/{tiendaId}")
	public ResponseEntity<?> buscarPorTienda(@PathVariable int tiendaId){
	    List<Departamento> depas = service.listarPorDepa(tiendaId);
	    return ResponseEntity.ok(depas);
	}

}

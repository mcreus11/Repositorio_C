package com.mx.Empleado.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Empleado.Entity.Empleado;
import com.mx.Empleado.Service.EmpleadoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/Empleados")
//@CrossOrigin
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Empleado>> listar() {
		List<Empleado> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{idEmpleado}")
	public ResponseEntity<Empleado> buscar(@PathVariable int idEmpleado) {
		Empleado empleado = service.buscar(idEmpleado);
		if (empleado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empleado);
	}
	
	@GetMapping("listarPorTienda/{tiendaId}")
	public ResponseEntity<List<Empleado>> listarPorTienda(@PathVariable int tiendaId) {
		List<Empleado> lista = service.listarPorTienda(tiendaId);
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lista);
	}


	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody Empleado empleado, BindingResult result) {
		
		Empleado empleadoguardar = service.guardar(empleado);
		
		if (empleadoguardar == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Ya existe un empleado con el mismo nombre, apellido, o contacto");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("El empleado con ID " + empleadoguardar.getIdEmpleado() + " se registró con éxito");
	}

	@PutMapping
	public ResponseEntity<?> editar(@Valid @RequestBody Empleado empleado, BindingResult result) {
		
		if (service.buscar(empleado.getIdEmpleado()) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("El ID del empleado no existe");
		}
		
		
		Empleado empleadoEditado = service.editar(empleado);
				
				if (empleadoEditado == null) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body("Ya existe un empleado con el mismo nombre, apellido, o contacto");
				}
				
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("El empleado con ID " + empleadoEditado.getIdEmpleado() + " se registró con éxito");
			}

	@DeleteMapping("/{idEmpleado}")
	public ResponseEntity<String> eliminar(@PathVariable int idEmpleado) {
		Empleado empleado = service.buscar(idEmpleado);
		
		if (empleado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("El ID no existe en la base de datos");
		} 
		
		service.eliminar(idEmpleado);
		return ResponseEntity.ok("Eliminación exitosa");
	}
}

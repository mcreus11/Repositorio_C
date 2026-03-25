package com.mx.Cliente.Controller;

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

import com.mx.Cliente.Entity.Cliente;
import com.mx.Cliente.Service.ClienteService;

@RestController
@RequestMapping("/Clientes")
//@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	// Endpoint 1: LISTAR TODOS (GET /clientes)
	@GetMapping
	public ResponseEntity<?> listar(){
	    List<Cliente> lista = service.listarAllClientes();
	    if(lista.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        // Devuelve 200 OK con la lista
	        return ResponseEntity.ok(lista);
	    }
	}
	
	// Endpoint 2: GUARDAR (POST /clientes)
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Cliente cliente){
	    // La lógica de existeCliente usa Nombre y Apellido (app)
	    boolean existe = service.existsCliente(cliente.getNombre(), cliente.getApp());
	    
	    if(existe) {
	        // Devuelve 302 FOUND
	        return ResponseEntity.status(HttpStatus.FOUND).body("El cliente " + cliente.getNombre() + 
	                                                            " " + cliente.getApp() + ", ya esta registrado en el sistema");
	    } else {
	        // Guardar el cliente
	        service.Save(cliente); 
	        // Devuelve 201 CREATED
	        return ResponseEntity.status(HttpStatus.CREATED).body("El cliente " + cliente.getNombre() + 
	                                                             " " + cliente.getApp() + ", se registro con exito");
	    }
	}
	
	// Endpoint 3: EDITAR/ACTUALIZAR (PUT /clientes/{idCliente})
	@PutMapping("/{idCliente}")
	public ResponseEntity<?> editar(@RequestBody Cliente cliente, @PathVariable int idCliente){
	    // 1. Verificar si el cliente a editar realmente existe
	    Cliente clienteExistente = service.FindCliente(idCliente);
	    
	    if (clienteExistente == null) {
	        // Devuelve 404 NOT FOUND
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No se encontró el cliente con ID " + idCliente);
	    }
	    
	    // 2. VALIDACIÓN CRÍTICA: Comprobar si otro cliente (diferente al actual) 
	    //    ya usa la combinación de Nombre y Apellido que se intenta guardar.
	    boolean existeOtro = service.existsAnotherClienteWithSameNameAndApp(
	        cliente.getNombre(), 
	        cliente.getApp(), 
	        idCliente // ID del cliente actual que debe ser excluido de la búsqueda
	    );
	    
	    if(existeOtro) {
	        // Devuelve 409 CONFLICT: Conflicto de datos, otro recurso ya tiene esa identidad
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Ya existe otro cliente registrado con el nombre y apellido: " + 
	                                                                cliente.getNombre() + " " + cliente.getApp());
	    }
	    
	    // 3. Si existe y NO hay duplicados, actualiza los datos del cliente existente
	    // Es importante establecer el ID del cliente existente para asegurar la actualización en la DB
	    clienteExistente.setNombre(cliente.getNombre());
	    clienteExistente.setApp(cliente.getApp());
	    clienteExistente.setEdad(cliente.getEdad());
	    clienteExistente.setTiendaId(cliente.getTiendaId());
	    
	    // 4. Guarda (actualiza) la entidad
	    service.Update(clienteExistente);
	    
	    // Devuelve 200 OK
	    return ResponseEntity.ok("El cliente con ID " + idCliente + " fue actualizado con éxito.");
	}

	// Endpoint 4: ELIMINAR (DELETE /clientes/{idCliente})
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> eliminar(@PathVariable int idCliente){
	    // 1. Verificar si el cliente existe antes de intentar eliminar
	    Cliente clienteExistente = service.FindCliente(idCliente);
	    
	    if (clienteExistente == null) {
	        // Devuelve 404 NOT FOUND
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No se encontró el cliente con ID " + idCliente + " para eliminar.");
	    }
	    
	    // 2. Elimina la entidad
	    service.Delete(idCliente);
	    
	    // Devuelve 200 OK
	    return ResponseEntity.ok("Cliente con ID " + idCliente + " eliminado con éxito.");
	}
	
	// Endpoint 5: BUSCAR POR ID (GET /clientes/{idCliente})
	@GetMapping("/{idCliente}")
	public ResponseEntity<?> buscarPorId(@PathVariable int idCliente){
	    Cliente cliente = service.FindCliente(idCliente);
	    
	    if (cliente == null) {
	        // Devuelve 404 NOT FOUND
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el cliente con ID " + idCliente);
	    }
	    
	    // Devuelve 200 OK con el objeto Cliente
	    return ResponseEntity.ok(cliente);
	}
	
	// Endpoint 6: BUSCAR POR TIENDA (GET /clientes/tienda/{tiendaId})
	@GetMapping("/buscarPorTienda/{tiendaId}")
	public ResponseEntity<?> buscarPorTienda(@PathVariable int tiendaId){
	    List<Cliente> clientes = service.findByTienda(tiendaId);
	    
	    if (clientes.isEmpty()) {
	        // Devuelve 204 NO CONTENT
	        return ResponseEntity.noContent().build();
	    }
	    
	    // Devuelve 200 OK con la lista de clientes
	    return ResponseEntity.ok(clientes);
	}
}

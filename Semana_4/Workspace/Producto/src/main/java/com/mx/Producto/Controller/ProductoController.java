package com.mx.Producto.Controller;

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

import com.mx.Producto.Entity.Producto;
import com.mx.Producto.Service.ProductoService;

@RestController
@RequestMapping("/Productos")
//@CrossOrigin
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
	    List<Producto> productos = service.listar();
	    if(productos.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(productos);
	    }
	}
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Producto producto) {
	    service.guardar(producto);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body("El producto " + producto.getNombre() + " se registró");
	}
	
	@PutMapping
	public ResponseEntity<?> editar(@RequestBody Producto producto){
	    Producto pro = service.buscar(producto.getIdProducto());
	    
	    if(pro == null) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("No se puede editar, el producto no está registrado");
	    } else {
	        service.editar(producto);
	        return ResponseEntity.accepted().body("El producto se edito con exito");
	    }
	}
	
	@DeleteMapping("/{idProducto}")
	public ResponseEntity<?> eliminar(@PathVariable int idProducto){
	    Producto pro = service.buscar(idProducto);

	    if(pro != null) {
	        service.eliminar(idProducto);
	        return ResponseEntity.ok("Eliminacion exitosa");
	    } else {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("No se puede eliminar, el id no existe en la base de datos");
	    }
	}
	
	@GetMapping("/{idProducto}")
	public ResponseEntity<?> buscar(@PathVariable int idProducto){
	    // Busca un producto por su ID
	    Producto producto = service.buscar(idProducto);

	    if(producto != null) {
	        return ResponseEntity.ok(producto);
	    }else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("listarPorTienda/{tiendaId}")
	public ResponseEntity<?> listarPorTienda(@PathVariable int tiendaId){
	    List<Producto> lista = service.listarPorTienda(tiendaId);

	    return ResponseEntity.ok(lista);
	}

}

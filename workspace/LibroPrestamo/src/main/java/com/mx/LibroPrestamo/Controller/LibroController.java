package com.mx.LibroPrestamo.Controller;

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

import com.mx.LibroPrestamo.Dominio.Libro;
import com.mx.LibroPrestamo.Service.LibroImplementacion;

@RestController
@RequestMapping(path = "/Libro")
@CrossOrigin
public class LibroController {

    // http://localhost:8010/Libro
    @Autowired
    private LibroImplementacion service;

    // listar
    @GetMapping("listar")
    // ResponseEntity es una clase que sirve para modificar el status y el body de la respuesta http
    public ResponseEntity<?> listar() {
        List<Libro> lista = service.listar();
        if(lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // no_content status 204
        } else {
            return ResponseEntity.ok(lista); // ok status 200
        }
    }
    
  //guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Libro libro){
        boolean encontrado = service.existeLibro(libro.getNombre());
        if(encontrado){
            return ResponseEntity.status(HttpStatus.FOUND).body("Este libro ya existe en la base de datos");
        } else {
            service.guardar(libro);
            return ResponseEntity.ok("El libro se registro con exito");
        }
    }
 // EDITAR
 // EN LibroController.java

    @PutMapping("editar")
    public ResponseEntity<?> editar(@RequestBody Libro libro) {
        // 1. Buscar la entidad existente en la BD.
        Libro encontrado = service.buscar(libro.getIdLibro());
        
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este libro que deseas editar no existe");
        } else {
           
            encontrado.setNombre(libro.getNombre());
            encontrado.setAutor(libro.getAutor());
            encontrado.setEditorial(libro.getEditorial());
            encontrado.setIsbn(libro.getIsbn());
            // ... cualquier otro campo
            
            // 3. Guardar el objeto 'encontrado' (el que Hibernate ya rastrea).
            // El dao.save() ahora actualizará el objeto correctamente.
            service.editar(encontrado);
            
            return ResponseEntity.ok("El libro " + encontrado.getNombre() + " se actualizó con éxito");
        }
    }
    
  //buscar
    @GetMapping("buscar/{idLibro}")
    public ResponseEntity<?> buscar(@PathVariable int idLibro){
        Libro encontrado = service.buscar(idLibro);
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro que buscas no existe");
        } else {
            return ResponseEntity.ok(encontrado);
        }
    }
    
  //eliminar
    @DeleteMapping("eliminar/{idLibro}")
    public ResponseEntity<?> eliminar(@PathVariable int idLibro){
        Libro encontrado = service.buscar(idLibro);
        if(encontrado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro que deseas eliminar no existe");
        } else {
        	service.eliminar(idLibro);
            return ResponseEntity.ok("El libro se elimino con exito");
        }
    }



}
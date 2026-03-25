package com.mx.Curso.Controller;

import java.util.NoSuchElementException;

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

import com.mx.Curso.Dominio.CursoRol;
import com.mx.Curso.Service.IRolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RolController {

    private final IRolService rolService;

    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    // listar
    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(rolService.getAllRoles());
    }

    // guardar
    @PostMapping
    public ResponseEntity<?> createRol(@Valid @RequestBody CursoRol rol) {
        try {
            CursoRol nuevoRol = rolService.saveRol(rol);
            return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRol(@PathVariable int id, @Valid @RequestBody CursoRol rolDetails) {
        try {
            CursoRol actualizado = rolService.updateRol(id, rolDetails);
            return ResponseEntity.ok(actualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Rol no encontrado con ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable int id) {
        try {
            rolService.deleteRol(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }
}

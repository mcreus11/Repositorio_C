package com.mx.Responsables.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Responsables.Entity.Responsable;
import com.mx.Responsables.Service.ResponsableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableController {

    @Autowired
    private ResponsableService service;

    @GetMapping
    public List<Responsable> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Responsable obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<Responsable> guardar(@Valid @RequestBody Responsable responsable) {
        return new ResponseEntity<>(service.guardar(responsable), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Responsable actualizar(@PathVariable Long id, @Valid @RequestBody Responsable responsable) {
        return service.actualizar(id, responsable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/veterinaria/{id}")
    public ResponseEntity<?> obtenerResponsableYVeterinaria(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getResponsableConVeterinaria(id));
    }

}

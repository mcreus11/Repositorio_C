package com.mx.Tienda.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Tienda.DTOs.Departamento;
import com.mx.Tienda.Entity.Tienda;
import com.mx.Tienda.Service.TiendaService;

@RestController
@RequestMapping("/T") 
//@CrossOrigin
public class TiendaController {

    @Autowired
    private TiendaService service;
    
    // 1. LISTAR TODAS: GET /T
    @GetMapping
    public ResponseEntity<List<Tienda>> listar() {
        List<Tienda> tiendas = service.listar();
        if (tiendas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tiendas);
    }
    
    // 2. BUSCAR POR ID: GET /T/{idTienda}
    @GetMapping("/{idTienda}")
    public ResponseEntity<?> buscar(@PathVariable int idTienda) {
        Tienda tienda = service.buscar(idTienda);
        if (tienda == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error, la tienda con ID " + idTienda + " no existe.");
        }
        return ResponseEntity.ok(tienda);
    }

    // 3. GUARDAR: POST /T
    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Tienda tienda) {
        // En una aplicación real, agregarías aquí una validación para duplicados (e.g., por nombre)
        service.guardar(tienda);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tienda guardada exitosamente.");
    }
    
    // 4. EDITAR: PUT /T
    @PutMapping
    public ResponseEntity<String> editar(@RequestBody Tienda tienda) {
        Tienda existente = service.buscar(tienda.getIdTienda());
        
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error, no se puede editar una tienda que no existe.");
        }
        // Se actualiza la entidad existente con los nuevos datos
        service.editar(tienda);
        return ResponseEntity.accepted().body("Tienda editada exitosamente.");
    }

    // 5. ELIMINAR: DELETE /T/{idTienda}
    @DeleteMapping("/{idTienda}")
    public ResponseEntity<String> eliminar(@PathVariable int idTienda) {
        Tienda tienda = service.buscar(idTienda);

        if (tienda == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error, no se puede eliminar una tienda que no existe.");
        }
        service.eliminar(idTienda);
        return ResponseEntity.accepted().body("Tienda eliminada exitosamente.");
    }
    
    // endpoint para listar los departamentos de la tienda
    @GetMapping("/depas/{tiendaId}")
    public ResponseEntity<?> listarDepartamentos(@PathVariable int tiendaId){
        try {
            Tienda tienda = service.buscar(tiendaId);
            if(tienda == null) {
                return ResponseEntity.notFound().build();
            } else {
                List<Departamento> depas = service.listarDepas(tiendaId);
                if(depas.isEmpty()) {
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.ok(depas);
                }
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Error, el servicio de Departamento no esta disponible");
        }
    }
    
    //
    @GetMapping("todo/{tiendaId}")
    public ResponseEntity<?> listadotodo(@PathVariable int tiendaId){
        Map<String, Object> listado = service.listarTodo(tiendaId);
        return ResponseEntity.ok(listado);
    }
}
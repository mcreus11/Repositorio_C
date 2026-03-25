package com.mx.DuenoMascota.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.DuenoMascota.Dominio.Dueño;
import com.mx.DuenoMascota.Service.DueñoImplementacion;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
@CrossOrigin
public class DueñoController {

	@Autowired
    private DueñoImplementacion service;

    //http://localhost:8011/api/duenos
    @GetMapping("listar")
    public ResponseEntity<List<Dueño>> listar() {
        List<Dueño> listaDueños = service.listar();
        if (listaDueños.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(listaDueños, HttpStatus.OK); 
    }

    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Dueño dueño){
       
        boolean encontrado = service.existeLibro(dueño.getNombre()); 
        
        if(encontrado){
           
            return ResponseEntity.status(HttpStatus.FOUND).body("ESTE DUEÑO YA EXISTE EN LA BASE DE DATOS");
        } else {
            service.guardar(dueño);
            return ResponseEntity.ok("EL DUEÑO SE REGISTRÓ CON ÉXITO");
        }
    }
    
 // EDITAR
    //http://localhost:8011/Dueno/editar
    @PutMapping("editar")
    public ResponseEntity<?> editar(@RequestBody Dueño dueño) {
        Dueño encontrado = service.buscar(dueño.getIdDueno());
        
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ESTE DUEÑO QUE DESEAS EDITAR NO EXISTE"); 
        } else {
            
            service.editar(dueño);
            
            return ResponseEntity.ok("EL DUEÑO " + dueño.getNombre() + " SE ACTUALIZÓ CON ÉXITO"); 
        }
    }

    // BUSCAR
    // http://localhost:8011/Dueno/buscar/{idDueño}
    @GetMapping("buscar/{idDueño}")
    public ResponseEntity<?> buscar(@PathVariable int idDueño){
        Dueño encontrado = service.buscar(idDueño);
        
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EL DUEÑO QUE BUSCAS NO EXISTE"); 
        } else {
            return ResponseEntity.ok(encontrado); 
        }
    }

    // ELIMINAR
    // http://localhost:8011/Dueno/eliminar/{idDueño}
    @DeleteMapping("eliminar/{idDueño}")
    public ResponseEntity<?> eliminar(@PathVariable int idDueño){
        Dueño encontrado = service.buscar(idDueño);
        
        if(encontrado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EL DUEÑO QUE DESEAS ELIMINAR NO EXISTE"); 
        } else {
            service.eliminar(idDueño);
            return ResponseEntity.ok("EL DUEÑO SE ELIMINÓ CON ÉXITO"); 
        }
    }
}

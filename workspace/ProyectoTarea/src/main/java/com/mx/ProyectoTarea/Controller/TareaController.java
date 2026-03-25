package com.mx.ProyectoTarea.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.ProyectoTarea.Dominio.Tarea;
import com.mx.ProyectoTarea.Service.ITareaService;
@RestController
@RequestMapping(path = "/api/tareas") 
@CrossOrigin
public class TareaController {

    @Autowired
    private ITareaService service;

    // listar
    @GetMapping("listar")
    public ResponseEntity<?> listar() {
        List<Tarea> lista = service.listar();
        if(lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.ok(lista); 
        }
    }

    // guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Tarea tarea){
            service.guardar(tarea);
            return ResponseEntity.status(HttpStatus.CREATED).body("La tarea se registró con éxito"); 
        
    }

    
    // editar
    @PutMapping("editar")
    public ResponseEntity<String> editar(@RequestBody Tarea tarea){
        service.editar(tarea);
        return ResponseEntity.ok("La tarea se editó con éxito");
    }

    // buscar
    @GetMapping("buscar/{idTarea}")
    public ResponseEntity<?> buscar(@PathVariable int idTarea){
        Tarea encontrado = service.buscar(idTarea);
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea que buscas no existe"); 
        } else {
            return ResponseEntity.ok(encontrado); 
        }
    }

    // eliminar
    @DeleteMapping("eliminar/{idTarea}")
    public ResponseEntity<?> eliminar(@PathVariable int idTarea){
        Tarea encontrado = service.buscar(idTarea);
        if(encontrado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea que deseas eliminar no existe"); 
        } else {
            service.eliminar(idTarea);
            return ResponseEntity.ok("La tarea se eliminó con éxito"); 
        }
    }


    // tareas/vencidas
    @GetMapping("vencidas")
    public ResponseEntity<?> getVencidas() {
        List<Tarea> vencidas = service.findTareasVencidas();
        if(vencidas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vencidas);
        }
    }
}
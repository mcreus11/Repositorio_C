package com.mx.ProyectoTarea.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.ProyectoTarea.Dominio.Proyecto;
import com.mx.ProyectoTarea.Service.IProyectoService;



@RestController
@RequestMapping(path = "/api/proyectos") 
@CrossOrigin
public class ProyectoController {

    @Autowired
    private IProyectoService service;

    // listar
    @GetMapping("listar")
    public ResponseEntity<?> listar() {
        List<Proyecto> lista = service.listar();
        if(lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.ok(lista); 
        }
    }

    // guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Proyecto proyecto){
    	service.guardar(proyecto); 
        
        return ResponseEntity.status(HttpStatus.CREATED).body("El proyecto se registró con éxito");
    }

    // editar
    @PutMapping("editar")
    public ResponseEntity<String> editar(@RequestBody Proyecto proyecto){
        service.editar(proyecto); 
        return ResponseEntity.ok("El proyecto se editó con éxito");
    }

    // buscar
    @GetMapping("buscar/{idProyecto}")
    public ResponseEntity<?> buscar(@PathVariable int idProyecto){
        Proyecto encontrado = service.buscar(idProyecto);
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto que buscas no existe"); 
        } else {
            return ResponseEntity.ok(encontrado); 
        }
    }

    // eliminar
    @DeleteMapping("eliminar/{idProyecto}")
    public ResponseEntity<?> eliminar(@PathVariable int idProyecto){
        Proyecto encontrado = service.buscar(idProyecto);
        if(encontrado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto que deseas eliminar no existe"); 
        } else {
            service.eliminar(idProyecto);
            return ResponseEntity.ok("El proyecto se eliminó con éxito"); 
        }
    }


    // proyectos/vencidos
    @GetMapping("vencidos")
    public ResponseEntity<?> getVencidos() {
        List<Proyecto> vencidos = service.findProyectosVencidos();
        if(vencidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vencidos);
        }
    }

    // nombre/{nombre}
    @GetMapping("nombre/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable String nombre) {
        List<Proyecto> encontrados = service.findProyectosPorNombre(nombre);
         if(encontrados.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(encontrados);
        }
    }
}
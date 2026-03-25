package com.mx.DuenoMascota.Controller;


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

import com.mx.DuenoMascota.Dominio.Mascota; 
import com.mx.DuenoMascota.Service.MascotaImplementacion; 

@RestController
@RequestMapping(path = "/Mascota") 
@CrossOrigin
public class MascotaController {

    @Autowired
    private MascotaImplementacion service;

    // http://localhost:8011/Mascota/listar
    @GetMapping("listar")
    public ResponseEntity<?> listar() {
        List<Mascota> lista = service.listar();
        if(lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.ok(lista); 
        }
    }

    // GUARDAR
    // http://localhost:8011/Mascota/guardar
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Mascota mascota){
        
        if (mascota.getIdMascota() <= 0) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: EL ID_MASCOTA DEBE SER PROPORCIONADO.");
        }
        
        try {
            service.guardar(mascota);
            return ResponseEntity.ok("LA MASCOTA SE REGISTRÓ CON ÉXITO");
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL GUARDAR LA MASCOTA: " + e.getMessage());
        }
    }

    // EDITAR
    // http://localhost:8011/Mascota/editar
    @PutMapping("editar")
    public ResponseEntity<?> editar(@RequestBody Mascota mascota) {
        
        Mascota encontrado = service.buscar(mascota.getIdMascota());
        
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ESTA MASCOTA QUE DESEAS EDITAR NO EXISTE"); 
        } else {
            
            service.editar(mascota);
            
            return ResponseEntity.ok("LA MASCOTA " + mascota.getNombre() + " SE ACTUALIZÓ CON ÉXITO"); 
        }
    }

    // BUSCAR
    // http://localhost:8011/Mascota/buscar/{idMascota}
    @GetMapping("buscar/{idMascota}")
    public ResponseEntity<?> buscar(@PathVariable int idMascota){
        Mascota encontrado = service.buscar(idMascota);
        
        if(encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LA MASCOTA QUE BUSCAS NO EXISTE"); 
        } else {
            return ResponseEntity.ok(encontrado); 
        }
    }

    // ELIMINAR
    // http://localhost:8011/Mascota/eliminar/{idMascota}
    @DeleteMapping("eliminar/{idMascota}")
    public ResponseEntity<?> eliminar(@PathVariable int idMascota){
        Mascota encontrado = service.buscar(idMascota);
        
        if(encontrado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("LA MASCOTA QUE DESEAS ELIMINAR NO EXISTE"); 
        } else {
            service.eliminar(idMascota);
            return ResponseEntity.ok("LA MASCOTA SE ELIMINÓ CON ÉXITO"); 
        }
    }
    
    @GetMapping("/especie/{especie}")
	public ResponseEntity<?> listarPorEspecie(@PathVariable String especie) {
		List<Mascota> lista = service.listarPorEspecie(especie); 

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SE ENCONTRARON MASCOTAS CON LA ESPECIE: " + especie);
		} else {
			return ResponseEntity.ok(lista);
		}
	}
}

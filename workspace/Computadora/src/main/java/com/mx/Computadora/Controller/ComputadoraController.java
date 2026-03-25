package com.mx.Computadora.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Computadora.Dominio.Computadora;
import com.mx.Computadora.Service.IComputadoraService;

@RestController 
@RequestMapping(path = "/api/compu") 
@CrossOrigin(origins = "*") 
public class ComputadoraController {

    @Autowired
    private IComputadoraService computadoraService;

    // http://localhost:8081/api/compu/listar
    @GetMapping(path = "/listar")
    public List<Computadora> listar() {
        return computadoraService.mostrar();
    }

    // GUARDAR
    @PostMapping(path = "/guardar")
    public ResponseEntity<String> guardar(@RequestBody Computadora computadora) {
        String mensaje = computadoraService.guardar(computadora);
        
        if (mensaje.contains("existe")) {
            return new ResponseEntity<String>(mensaje, HttpStatus.CONFLICT); 
        } else {
            return new ResponseEntity<String>(mensaje, HttpStatus.CREATED); 
        }
    }

    // EDITAR
    @PutMapping(path = "/editar")
    public ResponseEntity<String> editar(@RequestBody Computadora computadora) {
        String mensaje = computadoraService.editar(computadora);
        
        if (mensaje.contains("Error")) {
            return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND); 
        } else {
            return new ResponseEntity<String>(mensaje, HttpStatus.OK); 
        }
    }

    // ELIMINAR 
    @DeleteMapping(path = "/eliminar")
    public ResponseEntity<String> eliminar(@RequestBody Computadora computadora) {
        String mensaje = computadoraService.eliminar(computadora);
        
        if (mensaje.contains("Error")) {
            return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>(mensaje, HttpStatus.OK);
        }
    }

    // BUSCAR 
    @PostMapping(path = "/buscar")
    public ResponseEntity<?> buscar(@RequestBody Computadora computadora) {
        Computadora encontrada = computadoraService.buscar(computadora);
        
        if (encontrada != null) {
            return new ResponseEntity<Computadora>(encontrada, HttpStatus.OK); 
        } else {
            String mensaje = "La computadora con ID " + computadora.getId() + " no fue encontrada.";
            // 404 Not Found
            return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
        }
    }
}
package com.mx.LibroPrestamo.Controller;

import java.time.LocalDate;
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

import com.mx.LibroPrestamo.Dominio.Prestamo; 
import com.mx.LibroPrestamo.Service.PrestamoImplementacion; 

@RestController
@RequestMapping(path = "/Prestamo")
@CrossOrigin(origins = "http://localhost:4200") 
public class PrestamoController {

    // 1. Inyección de PrestamoService
    @Autowired
    private PrestamoImplementacion service;
    
    // 1. LISTAR TODOS LOS PRÉSTAMOS
    @GetMapping("listar")
    public ResponseEntity<?> listar() {
        try {
            List<Prestamo> lista = service.listar();
            if(lista.isEmpty()) {
                // Si la lista está vacía, devuelve 204 No Content
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
            } else {
                // Si hay datos, devuelve 200 OK con la lista
                return ResponseEntity.ok(lista); 
            }
        } catch (Exception e) {
             // Manejo de error general
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los préstamos: " + e.getMessage());
        }
    }

    // 2. GUARDAR UN NUEVO PRÉSTAMO
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(@RequestBody Prestamo prestamo){
        try {
            service.guardar(prestamo);
            // Devuelve 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body("El préstamo se registró con éxito"); 
        } catch (Exception e) {
            // Devuelve 400 Bad Request o 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el préstamo: " + e.getMessage()); 
        }
    }

    // 3. EDITAR UN PRÉSTAMO EXISTENTE
    // Mapea a PUT http://localhost:8010/Prestamo/editar
    @PutMapping("/editar")
    public ResponseEntity<String> editar(@RequestBody Prestamo prestamo){
        try {
            service.editar(prestamo);
            // Devuelve 200 OK
            return ResponseEntity.ok("El préstamo se editó con éxito");
        } catch (Exception e) {
            // Devuelve 500 Internal Server Error si falla la edición
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al editar el préstamo: " + e.getMessage());
        }
    }
 

    // 4. BUSCAR UN PRÉSTAMO POR ID
    @GetMapping("buscar/{idPrestamo}")
    public ResponseEntity<?> buscar(@PathVariable int idPrestamo){
        try {
            Prestamo encontrado = service.buscar(idPrestamo);
            if(encontrado == null) {
                // Devuelve 404 Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El préstamo que buscas no existe"); 
            } else {
                // Devuelve 200 OK
                return ResponseEntity.ok(encontrado); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el préstamo: " + e.getMessage());
        }
    }

    // 5. ELIMINAR UN PRÉSTAMO POR ID
    @DeleteMapping("eliminar/{idPrestamo}")
    public ResponseEntity<?> eliminar(@PathVariable int idPrestamo){
        try {
            Prestamo encontrado = service.buscar(idPrestamo);
            if(encontrado == null){
                // Devuelve 404 Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El préstamo que deseas eliminar no existe"); 
            } else {
                service.eliminar(idPrestamo);
                // Devuelve 200 OK
                return ResponseEntity.ok("El préstamo se eliminó con éxito"); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el préstamo: " + e.getMessage());
        }
    }

    // 6. OBTENER PRÉSTAMOS ACTIVOS
    @GetMapping("/activos")
    public ResponseEntity<?> obtenerActivos(){
        try {
            List<Prestamo> activos = service.obtenerPrestamosActivos();
            if(activos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(activos);
            }
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener préstamos activos: " + e.getMessage());
        }
    }
    
    // 7. ACTUALIZAR FECHA FIN (Para extender o devolver)
    @PutMapping("/{idPrestamo}/fecha-fin")
    public ResponseEntity<?> actualizarFechas(@PathVariable int idPrestamo, @RequestBody LocalDate fechaFin){
        try {
            boolean actualizacion = service.actualizarFechaFin(idPrestamo, fechaFin);

            if(actualizacion) {
                return ResponseEntity.ok("Fecha de finalización actualizada");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado, no se pudo actualizar");
            }
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la fecha: " + e.getMessage());
        }
    }
}

package com.mx.Persiste.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Persiste.Entity.Transaccion;
import com.mx.Persiste.Service.TransaccionService;

@RestController
@RequestMapping("/T")
@CrossOrigin
public class TransaccionController {

    @Autowired
    private TransaccionService service;

    // Listar 
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Transaccion> lista = service.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.ok("No hay transacciones registradas");
        }

        return ResponseEntity.ok(lista);
    }

    // Guardar
    @PostMapping
    public ResponseEntity<Transaccion> crear(@RequestBody Transaccion tx) {
        Transaccion guardada = service.guardar(tx);
        return ResponseEntity.ok(guardada);
    }


    // Buscar
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Transaccion encontrada = service.buscar(id);

        if (encontrada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la transacción con ID: " + id);
        }

        return ResponseEntity.ok(encontrada);
    }

    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable int id, @RequestBody Transaccion NTr) {
        Transaccion existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la transacción a editar con ID: " + id);
        }

        existente.setOperacion(NTr.getOperacion());
        existente.setImporte(NTr.getImporte());
        existente.setCliente(NTr.getCliente());
        existente.setEstatus(NTr.getEstatus());
        existente.setReferencia(NTr.getReferencia());

        service.guardar(existente);
        return ResponseEntity.ok("Transacción editada correctamente con ID: " + id);
    }

    // Actualizar estatus
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarEstatus(@PathVariable int id, @RequestBody Map<String, String> body) {

        String referencia = body.get("referencia");
        String estatus = body.get("estatus");
        Transaccion actualizada = service.ActualizarEstatus(id, referencia, estatus);
        if (actualizada == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("mensaje", "No se pudo actualizar la transacción"));
        }

        return ResponseEntity.ok(Map.of("id", actualizada.getId(),
                "mensaje", "Estatus actualizado correctamente",
                "estatus", actualizada.getEstatus()
        ));
    }


    // Eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (!service.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la transacción con ID: " + id + " para eliminar");
        }

        return ResponseEntity.ok("Transacción eliminada correctamente con ID: " + id);
    }
}

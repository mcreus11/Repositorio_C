package com.mx.Celular.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Celular.Dominio.Celular;
import com.mx.Celular.Service.CelularServiceImp;

@RestController
@RequestMapping(path = "/api/Cel")
@CrossOrigin
public class CelularController {

    @Autowired
    private CelularServiceImp service;

    @GetMapping(path = "/listar")
    public List<Celular> listar(){
        return service.mostrar();
    }

    @PostMapping(path = "/guardar")
    public void guardar(@RequestBody Celular celular) {
        service.guardar(celular);
    }
    
  //editar --> http://localhost:8081/api/Cel/editar
    @PutMapping(path = "editar")
    public void editar(@RequestBody Celular celular) {
        service.editar(celular);
    }

    //eliminar --> http://localhost:8081/api/Cel/eliminar
    @DeleteMapping(path = "eliminar")
    public void eliminar(@RequestBody Celular celular) {
        service.eliminar(celular);
    }

    //buscar --> http://localhost:8081/api/Cel/buscar
    @PostMapping(path="buscar")
    public void buscar(@RequestBody Celular celular) {
        service.buscar(celular);
    }
    
  //metodo que aplica descuento
    @PostMapping("/descuento")
    public ResponseEntity<String> aplicarDescuento(@RequestParam Integer pDescuento){
        service.aplicarDescueno(pDescuento);
        return ResponseEntity.ok("Descuento del " + pDescuento + "% aplicado correctamente");
    }
}

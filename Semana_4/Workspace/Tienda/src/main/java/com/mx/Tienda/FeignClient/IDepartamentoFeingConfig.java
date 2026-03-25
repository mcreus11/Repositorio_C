package com.mx.Tienda.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Tienda.DTOs.Departamento;

//determina como seremos clientes http, solicita el nombre, url
@FeignClient(name = "Departamento", url = "http://localhost:8013/Departamento") 
public interface IDepartamentoFeingConfig {

 @GetMapping("porTiendas/{tiendaId}")
 public List<Departamento> buscarPorTienda(@PathVariable int tiendaId);

}
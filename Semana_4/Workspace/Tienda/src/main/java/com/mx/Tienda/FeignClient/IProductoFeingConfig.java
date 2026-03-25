package com.mx.Tienda.FeignClient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mx.Tienda.DTOs.Producto; 

@FeignClient(name = "Producto", url = "http://localhost:8011/Productos") 
public interface IProductoFeingConfig {

    @GetMapping("/listarPorTienda/{tiendaId}") 
    public List<Producto> buscarPorTienda(@PathVariable int tiendaId);
}

package com.mx.Tienda.FeignClient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mx.Tienda.DTOs.Empleado; 

@FeignClient(name = "Empleado", url = "http://localhost:8012/Empleados") 
public interface IEmpleadoFeingConfig {

    @GetMapping("/listarPorTienda/{tiendaId}") 
    public List<Empleado> buscarPorTienda(@PathVariable int tiendaId);
}

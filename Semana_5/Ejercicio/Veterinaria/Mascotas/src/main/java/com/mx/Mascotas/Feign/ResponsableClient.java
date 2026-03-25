package com.mx.Mascotas.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Mascotas.Entidades.Responsable;

@FeignClient(name = "responsables", url = "http://localhost:8002", path = "/api/responsables")
public interface ResponsableClient {

    @GetMapping("/{id}")
    Responsable obtenerPorId(@PathVariable Long id);
}

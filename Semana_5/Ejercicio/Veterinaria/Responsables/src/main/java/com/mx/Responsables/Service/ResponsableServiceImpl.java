package com.mx.Responsables.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Responsables.Entidad.Veterinaria;
import com.mx.Responsables.Entity.Responsable;
import com.mx.Responsables.Feign.VeterinariaClient;
import com.mx.Responsables.Repository.ResponsableRepository;


@Service
public class ResponsableServiceImpl implements ResponsableService {

    @Autowired
    private ResponsableRepository repository;

    @Override
    public List<Responsable> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    public Responsable obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @Override
    public Responsable guardar(Responsable responsable) {
        return repository.save(responsable);
    }

    @Override
    public Responsable actualizar(Long id, Responsable responsable) {
        Responsable existente = obtenerPorId(id);
        existente.setNombre(responsable.getNombre());
        existente.setContacto(responsable.getContacto());
        existente.setVeterinariaId(responsable.getVeterinariaId());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    
    @Autowired
    private VeterinariaClient veterinariaClient;

    @Override
    public Map<String, Object> getResponsableConVeterinaria(Long id) {
        Map<String, Object> resultado = new HashMap<>();

        Responsable responsable = repository.findById(id).orElse(null);

        if (responsable == null) {
            resultado.put("mensaje", "Responsable no encontrado");
            return resultado;
        }

        resultado.put("responsable", responsable);

        try {
            Veterinaria veterinaria = veterinariaClient.obtener(responsable.getVeterinariaId());
            resultado.put("veterinaria", veterinaria);
        } catch (Exception e) {
            resultado.put("veterinaria mensaje", "Veterinaria no encontrada");
        }

        return resultado;
    }


	
}
package com.mx.Veterinaria.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Veterinaria.Entity.Veterinaria;
import com.mx.Veterinaria.Repository.VeterinariaRepository;

@Service
public class VeterinariaServiceImpl implements VeterinariaService {

	@Autowired
    private  VeterinariaRepository repository;


    @Override
    public List<Veterinaria> listar() {
        return repository.findAll();
    }

    @Override
    public Veterinaria guardar(Veterinaria veterinaria) {
        return repository.save(veterinaria);
    }

    @Override
    public Optional<Veterinaria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

	
    
   

}
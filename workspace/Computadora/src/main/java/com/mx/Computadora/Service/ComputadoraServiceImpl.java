package com.mx.Computadora.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.Computadora.Dao.IComputadoraDao;
import com.mx.Computadora.Dominio.Computadora;

@Service 
public class ComputadoraServiceImpl implements IComputadoraService {

    @Autowired
    private IComputadoraDao computadoraDao;


    @Override
    public String guardar(Computadora computadora) {
        Computadora existe = computadoraDao.findByMarca(computadora.getMarca());
        if (existe != null) {
            return "La computadora con Marca " + computadora.getMarca() + " ya existe en la base de datos.";
        } else {
            computadoraDao.save(computadora);
            return "La computadora se guardó con éxito.";
        }
    }

    @Override
    public String editar(Computadora computadora) {
        Optional<Computadora> existe = computadoraDao.findById(computadora.getId());
        if (existe.isPresent()) {
            computadoraDao.save(computadora); 
            return "La computadora con ID " + computadora.getId() + " se actualizó con éxito.";
        } else {
            return "Error: No se encontró la computadora con ID " + computadora.getId() + " para editar.";
        }
    }

    @Override
    public String eliminar(Computadora computadora) {
        Optional<Computadora> existe = computadoraDao.findById(computadora.getId());
        if (existe.isPresent()) {
            computadoraDao.delete(computadora);
            return "La computadora con ID " + computadora.getId() + " se eliminó con éxito.";
        } else {
            return "Error: No se encontró la computadora con ID " + computadora.getId() + " para eliminar.";
        }
    }

    @Override
    public Computadora buscar(Computadora computadora) {
        return computadoraDao.findById(computadora.getId()).orElse(null);
    }
    
    @Override
    public List<Computadora> mostrar() {
        return computadoraDao.findAll(); 
    }
}
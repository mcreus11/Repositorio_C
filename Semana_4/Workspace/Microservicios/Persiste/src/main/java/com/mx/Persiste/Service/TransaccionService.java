package com.mx.Persiste.Service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Persiste.Entity.Transaccion;
import com.mx.Persiste.Repository.TransaccionRepo;

@Service
public class TransaccionService {
	@Autowired
	private TransaccionRepo dao;
	
	
    private final Random random = new Random();
    
    public Transaccion guardar(Transaccion tx) {
        int n = 100000 + random.nextInt(900000);
        tx.setReferencia(String.valueOf(n));
        tx.setEstatus("Aprobada");
        return dao.save(tx);
    }
    
    public List<Transaccion> findAll() {
        return dao.findAll();
    }
    
    public Transaccion ActualizarEstatus(int id, String referencia, String estatus) {
    	Transaccion existe = dao.findById(id).orElse(null);
        if (existe == null) return null;
        if (referencia != null && !referencia.equals(existe.getReferencia())) {
            return null;
        }
        existe.setEstatus(estatus);
        return dao.save(existe);
    }
    
    public Transaccion buscar(int id) {
        return dao.findById(id).orElse(null);
    }
    
    public boolean eliminar(int id) {
        if (!dao.existsById(id)) return false;
        dao.deleteById(id);
        return true;
    }


    

	

}

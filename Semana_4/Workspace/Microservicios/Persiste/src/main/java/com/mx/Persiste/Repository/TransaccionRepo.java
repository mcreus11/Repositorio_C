package com.mx.Persiste.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Persiste.Entity.Transaccion;

@Repository
public interface TransaccionRepo extends JpaRepository<Transaccion, Integer>{
	Transaccion findByReferencia(String referencia);
}

package com.mx.Celular.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Celular.Dao.ICelularDao;
import com.mx.Celular.Dominio.Celular;

@Service //indica que esta clase contiene confirmaciones y que debe ser inyectada en otra clase
public class CelularServiceImp implements ICelularService {

    @Autowired // inyecta el repositorio para poder usar sus metodos. Patron de inversion de control
	private ICelularDao dao;
	@Override
	public void guardar(Celular celular) {
		dao.save(celular);
		
	}

	@Override
	public void editar(Celular celular) {
		dao.save(celular);
		
	}

	@Override
	public Celular buscar(Celular celular) {
		return dao.findById(celular.getIdCelular()).orElse(null);
	}

	@Override
	public void eliminar(Celular celular) {
		dao.delete(celular);
		
	}

	@Override
	public List<Celular> mostrar() {
		return (List<Celular>) dao.findAll();
	}
	
	//consumir el procedimiento
	public void aplicarDescueno(Integer pDescuento) {
	    if(pDescuento < 0 || pDescuento > 100) {
	        throw new IllegalArgumentException("El descuento debe estar en 0 y 100");
	    }
	    dao.aplicarDescuento(pDescuento);
	}

}

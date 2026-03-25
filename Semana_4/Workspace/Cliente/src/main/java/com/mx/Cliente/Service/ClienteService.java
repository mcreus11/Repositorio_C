package com.mx.Cliente.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.mx.Cliente.Entity.Cliente;
import com.mx.Cliente.Repository.ClienteRepository;

@Service
public class ClienteService {


	@Autowired
	private ClienteRepository dao;


	public void Save(Cliente cliente) {
	    dao.save(cliente);
	}

	public void Update(Cliente cliente) {
	    dao.save(cliente);
	}

	public void Delete(int idCliente) {
	    dao.deleteById(idCliente);
	}
	
	public Cliente FindCliente(int idCliente) {
	    return dao.findById(idCliente).orElse(null);
	}

	public boolean existsCliente(String nombre, String app) {
	    return dao.existsByNombreAndAppIgnoringCase(nombre, app);
	}
	public List<Cliente> findByTienda(int tiendaId){
		return dao.findByTiendaId(tiendaId);
		}
	
	public List<Cliente> listarAllClientes(){
		// CORREGIDO: findAll debe recibir un objeto Sort
		return dao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));	
		}
	
	public boolean existsAnotherClienteWithSameNameAndApp(String nombre, String app, int idClienteActual) {
	    return dao.existsByNombreAndAppIgnoringCaseAndIdClienteNot(nombre, app, idClienteActual);
	}
}
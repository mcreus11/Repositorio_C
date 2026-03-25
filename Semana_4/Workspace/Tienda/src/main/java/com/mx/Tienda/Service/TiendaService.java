package com.mx.Tienda.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.mx.Tienda.DTOs.Cliente;
import com.mx.Tienda.DTOs.Departamento;
import com.mx.Tienda.DTOs.Empleado;
import com.mx.Tienda.DTOs.Producto; 
import com.mx.Tienda.Entity.Tienda;
import com.mx.Tienda.FeignClient.IDepartamentoFeingConfig;
import com.mx.Tienda.FeignClient.IEmpleadoFeingConfig;
import com.mx.Tienda.FeignClient.IProductoFeingConfig; 

import feign.FeignException; 
import com.mx.Tienda.Repository.TiendaRepository;

@Service
public class TiendaService {

	@Autowired
	private TiendaRepository dao;
	
	public void guardar(Tienda tienda) { 
		dao.save(tienda); 
	}
	public void editar(Tienda tienda) { 
		dao.save(tienda); 
	}
	public void eliminar(int idTienda) { 
		dao.deleteById(idTienda);
	}
	public Tienda buscar(int idTienda) { 
		return dao.findById(idTienda).orElse(null); 
	}
	public List<Tienda> listar(){ 
		return dao.findAll(Sort.by(Sort.Direction.ASC, "nombre")); 
	}

	
	
	@Autowired
	private RestTemplate restTemp;

	public List<Cliente> listarClientes(int tiendaId) {
	    try {
	        @SuppressWarnings("unchecked")
	        List<Cliente> clientes = restTemp.getForObject(
	        		"http://localhost:8010/Clientes/buscarPorTienda/" + tiendaId,
	        		List.class);
	        
	        return clientes != null ? clientes : Collections.emptyList();
	    
	    } catch (ResourceAccessException e) {
	        System.err.println("Error de conexión con el servicio Clientes (RestTemplate): " + e.getMessage());
	        return Collections.emptyList(); 
	    } catch (Exception e) {
	        System.err.println("Error al obtener clientes: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}
	
	
	@Autowired
	private IDepartamentoFeingConfig depFC;
	
	public List<Departamento> listarDepas(int tiendaId) {
	    try {
            List<Departamento> departamentos = depFC.buscarPorTienda(tiendaId);
            return departamentos != null ? departamentos : Collections.emptyList();
        } catch (FeignException e) {
            System.err.println("Error de Feign con el servicio Departamento: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener departamentos: " + e.getMessage());
            return Collections.emptyList();
        }
	}
	
	
	@Autowired
	private IEmpleadoFeingConfig empFC;

	public List<Empleado> listarEmpleados(int tiendaId) {
	    try {
            List<Empleado> empleados = empFC.buscarPorTienda(tiendaId);
            return empleados != null ? empleados : Collections.emptyList();
        } catch (FeignException e) {
            System.err.println("Error de Feign con el servicio Empleado: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener empleados: " + e.getMessage());
            return Collections.emptyList();
        }
	}
	
	
	@Autowired
	private IProductoFeingConfig proFC; 

	public List<Producto> listarProductos(int tiendaId) {
	    try {
            List<Producto> productos = proFC.buscarPorTienda(tiendaId);
            return productos != null ? productos : Collections.emptyList();
        } catch (FeignException e) {
            System.err.println("Error de Feign con el servicio Producto: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener productos: " + e.getMessage());
            return Collections.emptyList();
        }
	}
	

	public Map<String, Object> listarTodo(int tiendaId){
		Map<String, Object> resultado = new HashMap<>();
		
		Tienda tiendita = dao.findById(tiendaId).orElse(null);
		
		if(tiendita == null) {
			resultado.put("Tienda mensaje: ", "No existe esta tienda");
			return resultado; 
		}
		
		resultado.put("Informacion de la tienda", tiendita);
	
		// 1. CLIENTES (RestTemplate)
		List<Cliente> clientes = listarClientes(tiendaId);
		if(clientes.isEmpty()) { 
			resultado.put("Clientes mensaje: ", "No existen clientes registrados en la tienda o el servicio falló");
		}else {
			resultado.put("Clientes asociados: ", clientes);
		}
		
		
		
		List<Departamento> departamentos = listarDepas(tiendaId);
		if(departamentos.isEmpty()) {
			resultado.put("Departamentos mensaje", "No existe informacion de los departamentos o el servicio falló");
		}else {
			resultado.put("Departamentos registrados", departamentos);
		}
        
      
		List<Empleado> empleados = listarEmpleados(tiendaId);
		if(empleados.isEmpty()) {
			resultado.put("Empleados mensaje", "No existe información de los empleados o el servicio falló");
		}else {
			resultado.put("Empleados registrados", empleados);
		}

        
       
		List<Producto> productos = listarProductos(tiendaId);
		if(productos.isEmpty()) {
			resultado.put("Productos mensaje", "No existe información de los productos o el servicio falló");
		}else {
			resultado.put("Productos registrados", productos);
		}


		return resultado;
	}
}
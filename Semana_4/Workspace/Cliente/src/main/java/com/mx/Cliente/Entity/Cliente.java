package com.mx.Cliente.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CLIENTE") 
@Data
public class Cliente {
    
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
    
	private String nombre;
	private String app;
	private int edad;
	private int tiendaId;

	public Cliente() {
		
	}

	public Cliente(int idCliente, String nombre, String app, int edad, int tiendaId) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.app = app;
		this.edad = edad;
		this.tiendaId = tiendaId;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTiendaId() {
		return tiendaId;
	}

	public void setTiendaId(int tiendaId) {
		this.tiendaId = tiendaId;
	}
	
	
	
}
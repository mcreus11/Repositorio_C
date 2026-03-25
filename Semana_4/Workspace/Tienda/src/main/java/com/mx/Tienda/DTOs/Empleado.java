package com.mx.Tienda.DTOs;

import lombok.Data;

@Data
public class Empleado {
	private int idEmpleado;
	private String nombre;
	private String app;
	private long contacto;
	private int edad;
	private int tiendaId;

}

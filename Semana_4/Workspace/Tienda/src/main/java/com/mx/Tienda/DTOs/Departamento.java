package com.mx.Tienda.DTOs;

import lombok.Data;

@Data
public class Departamento {
	private int idDepartamento;
	private String nombre;
	private String encargado;
	private int tiendaId;

}

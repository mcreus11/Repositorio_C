package com.mx.Empleado.Entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Empleado {
	@Id
    @Column(name = "ID_PRODUCTO") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpleado;
	private String nombre;
	private String app;
	private long contacto;
	@Min(value = 18, message = "El empleado debe ser mayor de 18 años.")
	private int edad;
	private int tiendaId;
	

}

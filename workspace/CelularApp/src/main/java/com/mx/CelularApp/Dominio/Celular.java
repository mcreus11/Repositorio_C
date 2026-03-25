package com.mx.CelularApp.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //indica que esta clase representa una entidad en la base de datos
@Table(name = "CELULAR") //mapea mi clase con la tabla en la base de datos
public class Celular {
    
    @Id //mapea que mi atributo es la llave primaria de mi tabla
    private int idCelular;
    
    @Column(name = "MARCA", columnDefinition = "NVARCHAR2(100)")
    private String marca;
    
    @Column(name = "MODELO", columnDefinition = "NVARCHAR2(100)")
    private String modelo;
    
    @Column(name = "RAM", columnDefinition = "NVARCHAR2(100)")
    private String ram;
    
    @Column(name = "PROCESADOR", columnDefinition = "NVARCHAR2(100)")
    private String procesador;
    
    @Column(name = "PRECIO", columnDefinition = "NUMBER")
    private int precio;
    
    
    public Celular() {
    	
    }


	public Celular(int idCelular, String marca, String modelo, String ram, String procesador, int precio) {
		super();
		this.idCelular = idCelular;
		this.marca = marca;
		this.modelo = modelo;
		this.ram = ram;
		this.procesador = procesador;
		this.precio = precio;
	}


	public int getIdCelular() {
		return idCelular;
	}


	public void setIdCelular(int idCelular) {
		this.idCelular = idCelular;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getRam() {
		return ram;
	}


	public void setRam(String ram) {
		this.ram = ram;
	}


	public String getProcesador() {
		return procesador;
	}


	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Celular [idCelular=" + idCelular + ", marca=" + marca + ", modelo=" + modelo + ", ram=" + ram
				+ ", procesador=" + procesador + ", precio=" + precio + "]";
	}
	
	
    
    
}


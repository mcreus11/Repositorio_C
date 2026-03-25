package com.mx.Computadora;

public class Computadora {
	//atributos 
	private String marca;
	private String modelo;
	private double precio;
	private int ram;
	private String capacidad;
	
	//constructor por defecto 
	public Computadora() {
		
	}
	
	//constructor con parametros : ayudar a crear la instancia de mi objeto 
	public Computadora(String marca, String modelo, double precio, int ram, String capacidad) {
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.ram = ram;
		this.capacidad = capacidad;
	}
	
	//getter y setter
	//get: obtener el valor del atributo 
	public String getMarca() {
		return marca;
	}
	
	//set: fijar el valor 
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public int getRam() {
		return ram;
	}
	
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public String getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	
	public String toString() {
		return "Computadora[marca= "+marca+" modelo= "+modelo+" precio= "+precio+" ram= "+ram+
				" capacidad= "+capacidad+"]\n";
	}
	
	
	

}

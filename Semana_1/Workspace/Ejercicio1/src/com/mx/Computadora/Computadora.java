package com.mx.Computadora;

public class Computadora {

	private String marca;
	private String modelo;
	private double precio;
	private int ram;
	private String capacidad;
	
	public Computadora() {
		
	}
	
	
	public Computadora(String marca, String modelo, double precio, int ram, String capacidad) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.ram = ram;
		this.capacidad = capacidad;
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


	@Override
	public String toString() {
		return "Computadora [marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", ram=" + ram
				+ ", capacidad=" + capacidad + "] \n";
	}
	
	
	
}

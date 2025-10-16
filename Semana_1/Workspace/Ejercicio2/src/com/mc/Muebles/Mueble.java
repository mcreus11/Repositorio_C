package com.mc.Muebles;

public class Mueble {
	private String color;
	private double precio;
	private int noPiezas;
	private String material;
	private String ubicacion;
	
	public Mueble() {
		
	}

	public Mueble(String color, double precio, int noPiezas, String material, String ubicacion) {
		super();
		this.color = color;
		this.precio = precio;
		this.noPiezas = noPiezas;
		this.material = material;
		this.ubicacion = ubicacion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getNoPiezas() {
		return noPiezas;
	}

	public void setNoPiezas(int noPiezas) {
		this.noPiezas = noPiezas;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Mueble [color=" + color + ", precio=" + precio + ", noPiezas=" + noPiezas + ", material=" + material
				+ ", ubicacion=" + ubicacion + "]\n";
	}
	
	

}

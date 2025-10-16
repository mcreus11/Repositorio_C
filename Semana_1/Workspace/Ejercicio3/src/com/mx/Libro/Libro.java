package com.mx.Libro;

public class Libro {
	private int clave;
	private String titulo;
	private String autor;
	private String editorial;
	private int año;
	private double precio;
	
	public Libro(int clave, String titulo, String autor, String editorial, int año, double precio) {
		super();
		this.clave = clave;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.año = año;
		this.precio = precio;
	}
	
	public Libro(int clave) {
		this.clave = clave;
	}


	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Libro [clave=" + clave + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", año=" + año + ", precio=" + precio + "]\n";
	}
	
	
}


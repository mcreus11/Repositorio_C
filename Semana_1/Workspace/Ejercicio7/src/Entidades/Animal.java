package Entidades;

public class Animal {
	protected String nombre;
	protected String color;
	protected String tamanio;
	protected String tipoReproduccion;
	
	public void hacerSonido() {
		System.out.println("el animal hace un sonido");
	}
	public Animal() {
		
	}
	public Animal(String nombre, String color, String tamanio, String tipoReproduccion) {
		super();
		this.nombre = nombre;
		this.color = color;
		this.tamanio = tamanio;
		this.tipoReproduccion = tipoReproduccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public String getTipoReproduccion() {
		return tipoReproduccion;
	}
	public void setTipoReproduccion(String tipoReproduccion) {
		this.tipoReproduccion = tipoReproduccion;
	}
	
	
	

}

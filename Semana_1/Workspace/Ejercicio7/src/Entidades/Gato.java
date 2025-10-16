package Entidades;

public class Gato extends Animal {

	public Gato(String nombre, String color, String tamanio, String tipoReproduccion) {
		super(nombre, color, tamanio, tipoReproduccion);
	}
	
	@Override
	public void hacerSonido() {
		System.out.println("El gato hace muau miau");
	}
	
	

}

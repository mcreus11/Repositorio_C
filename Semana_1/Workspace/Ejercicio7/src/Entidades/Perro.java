package Entidades;

public class Perro extends Animal{
	
	@Override
	public void hacerSonido() {
		System.out.println("el perro hace gua gua");
	}

	public Perro(String nombre, String color, String tamanio, String tipoReproduccion) {
		super(nombre, color, tamanio, tipoReproduccion);
		
	}
	
	

}

package Principal;

import Entidades.Gato;
import Entidades.Perro;

public class Principal {
	public static void main(String[] args) {
		Gato gato1 = new Gato("kitty", "amarillo", "pequenio", "viviparo");
		Perro perro = new Perro("furus", "cafe", "grande", "viviparo");
		
		gato1.hacerSonido();
		perro.hacerSonido();
	}

}

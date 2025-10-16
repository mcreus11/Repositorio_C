package celular;

import com.mx.HashMap.Mueble;

public class Principal {
	public static void main(String[]args) {
	
	Celular c1 = new Celular("Iphone", "negro", 8, 15999, 128);
	Celular c2 = new Celular("Samsumg", "gris", 8, 12999, 128);
	Celular c3 = new Celular("Xiaomi", "azul", 16, 11999, 248);
	Celular c4 = new Celular("Realme", "blanco", 8, 8999, 128);
	
	Celular aux = null;
	Implementacion imp = new Implementacion();
	
	imp.guardar(c1);
	imp.guardar(c2);
	imp.guardar(c3);
	imp.guardar(c4);
	
	imp.mostrar();
	//buscar
	aux = new Celular("Iphone");
	aux = imp.buscar(aux);
	System.out.println(aux);
	//editar
	
	aux = new Celular("Realme");
	aux = imp.buscar(aux);
	aux.setPrecio(4200.21);
	System.out.println("Actualizacion" + aux);
	
	//eliminar
	aux = new Celular("Xiaomi");
	aux = imp.buscar(aux);
	imp.eliminar(aux);
	imp.mostrar();
	
	}
}

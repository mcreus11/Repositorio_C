package celular;

import java.util.HashMap;

public class Implementacion implements Metodos{

	HashMap<String, Celular> hash = new HashMap<String, Celular>();
	@Override
	public void guardar(Celular celular) {
		hash.put(celular.getMarca(), celular);
		
	}

	@Override
	public Celular buscar(Celular celular) {
		return hash.get(celular.getMarca());
	}

	@Override
	public void editar(Celular celular) {
		hash.replace(celular.getMarca(), celular);
		
	}

	@Override
	public void eliminar(Celular celular) {
		hash.remove(celular.getMarca());
		
	}

	@Override
	public void mostrar() {
		System.out.println(hash);
		
	}

}

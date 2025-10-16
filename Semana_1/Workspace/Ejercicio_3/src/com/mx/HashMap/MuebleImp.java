package com.mx.HashMap;

import java.util.HashMap;

public class MuebleImp implements IMetodos{
	
	HashMap<String, Mueble> hash = new HashMap<String, Mueble>();

	@Override
	public void guardar(Mueble mueble) {
		hash.put(mueble.getNombre(), mueble);
		
	}

	@Override
	public Mueble buscar(Mueble mueble) {
		return hash.get(mueble.getNombre());
	}

	@Override
	public void editar(Mueble mueble) {
		hash.replace(mueble.getNombre(), mueble);
		
	}

	@Override
	public void eliminar(Mueble mueble) {
		hash.remove(mueble.getNombre());
		
	}

	@Override
	public void mostrar() {
		System.out.println(hash);
		
	}

}

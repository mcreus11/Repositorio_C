package com.mc.Muebles;

import java.util.ArrayList;
import java.util.List;

public class Implementacion implements IMetodos{

	List<Mueble> lista = new ArrayList<Mueble>();
	
	@Override
	public void create(Mueble mueble) {
		lista.add(mueble);
		
	}

	@Override
	public void update(int indice, Mueble mueble) {
		lista.set(indice, mueble);
		
	}

	@Override
	public void read() {
		System.out.println(lista);
		
	}

	@Override
	public void delete(int indice) {
		lista.remove(indice);
		
	}

	@Override
	public Mueble buscar(int indice) {
		return lista.get(indice);
	}
	
	//metodo personalizado
	public void contar() {
		System.out.println("Existen " + lista.size() + "muebles registrados");
	}



}
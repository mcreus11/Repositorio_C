package com.mc.Muebles;

public interface IMetodos {
	public void create(Mueble mueble);
	public void update(int indice, Mueble mueble);
	public void read();
	public void delete(int indice);
	public Mueble buscar(int indice);

}

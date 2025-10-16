package com.mx.Libro;

public interface IMetodos {
    public void create(Libro libro);
    public void update(int indice, Libro libro);
    public void read();
    public void delete(int indice);
    public Libro buscar(int indice);
}

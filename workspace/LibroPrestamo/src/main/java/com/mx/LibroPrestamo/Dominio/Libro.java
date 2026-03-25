package com.mx.LibroPrestamo.Dominio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIBROS")
public class Libro {
    
    @Id
    @Column(name = "ID_LIBROS")
    private int idLibro;
    @Column(name = "NOMBRE")
    private String nombre;
    private String autor;
    private String editorial;
    private Long isbn;
    @JsonManagedReference 
    @OneToMany(mappedBy = "libroId", cascade = CascadeType.ALL)
	List<Prestamo> list = new ArrayList<>();
    
    public Libro() {
        
    }

	public Libro(int idLibro, String nombre, String autor, String editorial, Long isbn) {
		super();
		this.idLibro = idLibro;
		this.nombre = nombre;
		this.autor = autor;
		this.editorial = editorial;
		this.isbn = isbn;
	}
	
	public List<Prestamo> getList() {
        return list;
    }

    public void setList(List<Prestamo> list) {
        this.list = list;
    }

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
    
}
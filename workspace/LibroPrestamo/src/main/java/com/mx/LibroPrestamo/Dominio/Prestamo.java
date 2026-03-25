package com.mx.LibroPrestamo.Dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Prestamo {
	@Id
	private int idPrestamo;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	@JsonBackReference 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LIBRO_ID")
	private Libro libroId;

	
	public Prestamo() {

	}

	public Prestamo(int idPrestamo, LocalDate fechaInicio, LocalDate fechaFin, Libro libroId) {
		super();
		this.idPrestamo = idPrestamo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.libroId = libroId;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Libro getLibroId() {
		return libroId;
	}

	public void setLibroId(Libro libroId) {
		this.libroId = libroId;
	}
	
	

}

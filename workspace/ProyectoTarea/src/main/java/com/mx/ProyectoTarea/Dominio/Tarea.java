package com.mx.ProyectoTarea.Dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarea {
    
	@Id
    @Column(name = "ID_TAREA") 
    private int id;
    
    private String titulo;
    private String descripcion;
    
    @Column(name = "ASIGNADO_A") 
    private String asignadoA;
    
    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion;
    
    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechaVencimiento;
    
    private String estado; 
    private String prioridad;
    
    @Column(name = "PROYECTO_ID")
    private Integer proyectoId; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROYECTO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @JsonIgnore
    private Proyecto proyecto;
    public Tarea() {
    	
    }

	public Tarea(int id, String titulo, String descripcion, String asignadoA, LocalDate fechaCreacion,
			LocalDate fechaVencimiento, String estado, String prioridad, Integer proyectoId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.asignadoA = asignadoA;
		this.fechaCreacion = fechaCreacion;
		this.fechaVencimiento = fechaVencimiento;
		this.estado = estado;
		this.prioridad = prioridad;
		this.proyectoId = proyectoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int idTarea) {
		this.id = idTarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public Integer getProyectoId() {
		return proyectoId;
	}

	public void setProyectoId(Integer proyectoId) {
		this.proyectoId = proyectoId;
	}

	
}

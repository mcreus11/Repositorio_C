package com.mx.DuenoMascota.Dominio;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "DUENO")
public class Dueño {

    @Id
    @Column(name = "ID_DUENO")
    private int idDueno;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TELEFONO")
    private String telefono;

    @OneToMany(mappedBy = "dueñoid", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;
    public Dueño() {
    	
    }
	public Dueño(int idDueno, String nombre, String apellido, String telefono) {
		super();
		this.idDueno = idDueno;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}
	public int getIdDueno() {
		return idDueno;
	}
	public void setIdDueno(int idDueno) {
		this.idDueno = idDueno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
    

}

package com.mx.DuenoMascota.Dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "MASCOTA")
public class Mascota {

    @Id
    @Column(name = "ID_MASCOTA")
    private int idMascota;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ESPECIE")
    private String especie;
    
    @Column(name = "EDAD")
    private Integer edad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DUENO_ID", referencedColumnName = "ID_DUENO") 
    @JsonIgnore
    private Dueño dueñoid;
    
    public Mascota() {
    	
    }

	public Mascota(int idMascota, String nombre, String especie, Integer edad, Dueño dueño) {
		super();
		this.idMascota = idMascota;
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.dueñoid = dueño;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Dueño getDueño() {
		return dueñoid;
	}

	public void setDueño(Dueño dueño) {
		this.dueñoid = dueño;
	}
    
    

}

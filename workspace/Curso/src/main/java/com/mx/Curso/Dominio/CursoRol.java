package com.mx.Curso.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CURSO_ROLES")
public class CursoRol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURSO_ROLES_SEQ")
    @SequenceGenerator(name = "CURSO_ROLES_SEQ", sequenceName = "CURSO_ROLES_SEQ", allocationSize = 1)
    @Column(name = "ID_ROL")
    private int idRol;

    @Column(name = "PRIVILEGIO", unique = true, nullable = false, length = 50)
    private String privilegio;
    
    public CursoRol() {
    	
    }

	public CursoRol(int idRol, String privilegio) {
		super();
		this.idRol = idRol;
		this.privilegio = privilegio;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}
    
    
    
    
    
}

package com.mx.Curso.Dominio;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "CURSO_USUARIOS")
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURSO_USUARIOS_SEQ")
    @SequenceGenerator(name = "CURSO_USUARIOS_SEQ", sequenceName = "CURSO_USUARIOS_SEQ", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private int idUsuario;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APP", nullable = false, length = 50)
    private String app; 

    @Column(name = "APM", length = 50)
    private String apm; 

    @Column(name = "SEXO", nullable = false, length = 25)
    private String sexo;

    @Column(name = "CORREO", unique = true, nullable = false, length = 100)
    private String correo; 

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Date fechaCreacion = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROL_ID", nullable = false)
    private CursoRol rol;
    
    public CursoUsuario() {
    	
    }

	public CursoUsuario(int idUsuario, String nombre, String app, String apm, String sexo, String correo,
			LocalDate fechaNacimiento, Date fechaCreacion, CursoRol rol) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.app = app;
		this.apm = apm;
		this.sexo = sexo;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaCreacion = fechaCreacion;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getApm() {
		return apm;
	}

	public void setApm(String apm) {
		this.apm = apm;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public CursoRol getRol() {
		return rol;
	}

	public void setRol(CursoRol rol) {
		this.rol = rol;
	}
    
    
    
    
    
}

package com.mx.Producto.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data 
public class Producto {

    @Id
    @Column(name = "ID_PRODUCTO") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    private String nombre;
    
    private String tipo;

    private String caracteristica;

    private double precio;

    private int stock;

    private int tiendaId;
    
    public Producto () {
    	
    }

	public Producto(int idProducto, String nombre, String tipo, String caracteristica, double precio, int stock,
			int tiendaId) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.tipo = tipo;
		this.caracteristica = caracteristica;
		this.precio = precio;
		this.stock = stock;
		this.tiendaId = tiendaId;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getTiendaId() {
		return tiendaId;
	}

	public void setTiendaId(int tiendaId) {
		this.tiendaId = tiendaId;
	}

	

	
	
	

    

}


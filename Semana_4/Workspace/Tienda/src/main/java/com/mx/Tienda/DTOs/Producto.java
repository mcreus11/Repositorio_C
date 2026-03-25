package com.mx.Tienda.DTOs;

import lombok.Data;

@Data
public class Producto {
	private int idProducto;

    private String nombre;
    
    private String tipo;

    private String caracteristica;

    private double precio;

    private int stock;

    private int tiendaId;

}

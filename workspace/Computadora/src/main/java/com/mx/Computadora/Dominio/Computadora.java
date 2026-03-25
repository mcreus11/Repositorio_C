package com.mx.Computadora.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity 
@Table(name = "COMPUTADORA") 
public class Computadora {
    
    @Id 
    @Column(name = "ID")
    private int id; 
    
    @Column(name = "MARCA", columnDefinition = "NVARCHAR2(50)")
    private String marca; 
    
    @Column(name = "RAM", columnDefinition = "INT")
    private int ram; 
    
    @Column(name = "ALMACENAMIENTO", columnDefinition = "INT")
    private int almacenamiento;
    
    @Column(name = "PROCESADOR", columnDefinition = "NVARCHAR2(50)")
    private String procesador; 
    
    @Column(name = "TIPO", columnDefinition = "INT")
    private int tipo; 
    
    @Column(name = "PRECIO", columnDefinition = "DECIMAL(10, 2)")
    private double precio; 
    
    @Column(name = "FECHA_COMPRA") 
    private LocalDate fechaCompra; 

    
    public Computadora() {
        
    }

    
    public Computadora(int id, String marca, int ram, int almacenamiento, String procesador, int tipo, double precio,
            LocalDate fechaCompra) {
        super();
        this.id = id;
        this.marca = marca;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.procesador = procesador;
        this.tipo = tipo;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Computadora [id=" + id + ", marca=" + marca + ", ram=" + ram + ", almacenamiento=" + almacenamiento
                + ", procesador=" + procesador + ", tipo=" + tipo + ", precio=" + precio + ", fechaCompra="
                + fechaCompra + "]";
    }
}
package com.mx.Persiste.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Transaccion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String operacion;
    private double importe;
    private String cliente;
    private String referencia;
    private String estatus;

}

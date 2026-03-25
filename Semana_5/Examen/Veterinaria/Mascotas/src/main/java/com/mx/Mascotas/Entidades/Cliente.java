package com.mx.Mascotas.Entidades;

import lombok.Data;

@Data
public class Cliente {

    private Long idCliente;
    private String nombre;
    private String direccion;
    private Long contacto;

}

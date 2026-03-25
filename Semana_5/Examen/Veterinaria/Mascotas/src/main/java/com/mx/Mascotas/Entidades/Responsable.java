package com.mx.Mascotas.Entidades;

import lombok.Data;

@Data
public class Responsable {
    private Long idResponsable;
    private String nombre;
    private String contacto;
    private Long veterinariaId;
}

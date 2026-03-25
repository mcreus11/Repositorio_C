package com.mx.Mascotas.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaClienteDTO {
	private Long idMascota;
    private String nombre;
    private String raza;
    private int edad;
    private String razonCita;

    private ClienteDTO cliente;
}

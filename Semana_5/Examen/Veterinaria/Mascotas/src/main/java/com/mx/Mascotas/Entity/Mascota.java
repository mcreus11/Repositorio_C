package com.mx.Mascotas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MASCOTAS")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    @NotBlank
    private String nombre;

    @NotBlank
    private String raza;

    @Min(0)
    private int edad;

    @NotBlank
    private String razonCita;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long responsableId;

    @NotNull
    @Column(name = "VETERINARIA_ID")
    private Long veterinariaId;
}
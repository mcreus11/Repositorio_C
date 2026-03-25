package com.mx.Validadora.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Transaccion {

    private Integer id;

    @NotBlank(message = "La operación es requerida")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
    private String operacion;

    @NotNull(message = "El importe es requerido")
    @Min(value = 0)
    private Double importe; 

    @NotBlank(message = "El cliente es requerido")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
    private String cliente;

    @NotBlank(message = "El SHA es requerido")
    private String sha;

    private String referencia;
    private String estatus;

}

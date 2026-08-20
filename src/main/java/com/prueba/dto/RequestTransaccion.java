package com.prueba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class RequestTransaccion {

    @NotNull(message = "Operacion no puede ir nulo")
    @NotBlank(message = "Operación no puede ir vacio")
    @Pattern(
            regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$",
            message = "Operación no puede tener números"
    )
    private String operacion;
    @NotNull(message = "Importe no puede ir nulo")
    @NotBlank(message = "Importe no puede ir vacio")
    @Pattern(
            regexp = "^\\d+(\\.\\d{1,2})?$",
            message = "Importe no puede tener números"
    )
    private String importe;
    @NotNull(message = "Cliente no puede ir nulo")
    @NotBlank(message = "Cliente no puede ir vacio")
    @Pattern(
            regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$",
            message = "Cliente no puede tener números"
    )
    private String cliente;
    private String secreto;

}

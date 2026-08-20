package com.prueba.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransaccion {

    private String id;
    private String status;
    private String referencia;
    private String operacion;

}

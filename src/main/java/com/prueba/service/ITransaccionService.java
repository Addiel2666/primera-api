package com.prueba.service;

import com.prueba.dto.RequestTransaccion;
import com.prueba.dto.ResponseTransaccion;

public interface ITransaccionService {

    ResponseTransaccion transaccionOperacion(RequestTransaccion request);

}

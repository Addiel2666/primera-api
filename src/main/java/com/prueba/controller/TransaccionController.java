package com.prueba.controller;

import com.prueba.dto.RequestTransaccion;
import com.prueba.service.ITransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaccion")
public class TransaccionController {

    @Autowired
    ITransaccionService transaccionService;

    @PostMapping("/proceso")
    public ResponseEntity<?> procesoTranssaccion(@Valid @RequestBody RequestTransaccion request){
        return ResponseEntity.ok(transaccionService.transaccionOperacion(request));
    }

}

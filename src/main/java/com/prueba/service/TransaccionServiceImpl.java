package com.prueba.service;

import com.prueba.decrypt.Aes256;
import com.prueba.dto.RequestTransaccion;
import com.prueba.dto.ResponseTransaccion;
import com.prueba.dto.ServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TransaccionServiceImpl implements ITransaccionService{

    private final ServiceProperties serviceProperties;

    private final RestTemplate restTemplate;

    public TransaccionServiceImpl(ServiceProperties serviceProperties, RestTemplate restTemplate) {
        this.serviceProperties = serviceProperties;
        this.restTemplate = restTemplate;
    }

    public ResponseTransaccion transaccionOperacion(RequestTransaccion request) {
        try {
            // El secreto llega cifrado desde el consumidor de esta primera API.
            String secretoDescifrado = Aes256.decrypt(
                    request.getSecreto(),
                    serviceProperties.secretkey()
            );
            request.setSecreto(secretoDescifrado);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RequestTransaccion> entity = new HttpEntity<>(request, headers);

            ResponseEntity<ResponseTransaccion> response = restTemplate.postForEntity(
                    serviceProperties.endpoint(),
                    entity,
                    ResponseTransaccion.class
            );

            if (response.getBody() == null) {
                throw new IllegalStateException("La segunda API respondió sin contenido");
            }

            return response.getBody();
        } catch (RestClientException e) {
            log.error("Error al consumir la segunda API: {}", e.getMessage(), e);
            throw new IllegalStateException("No fue posible comunicarse con la segunda API", e);
        } catch (Exception e) {
            log.error("Error procesando la transacción: {}", e.getMessage(), e);
            throw new IllegalArgumentException("No fue posible descifrar o procesar el secreto", e);
        }
    }
}

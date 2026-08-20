package com.prueba;

import com.prueba.dto.ServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ServiceProperties.class)
public class PrimeraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeraApiApplication.class, args);
	}

}

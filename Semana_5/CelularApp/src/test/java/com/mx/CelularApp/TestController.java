package com.mx.CelularApp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// indica al servidor Spring que use un puerto aleatorio para evitar conflictos
// esta anotacion crea un contexto de prueba completo para la aplicacion
public class TestController {

    @LocalServerPort // inyecta el puerto asignado cuando se construya el Url
    private int port;

    @Autowired
    TestRestTemplate restTemplate; // permite enviar solicitudes http simuladas y obtener respuestas

    @Test
    void MessageReturnsDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .contains("Hola, Mundo");
    }
}

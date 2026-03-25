package com.mx.Validadora.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mx.Validadora.Entity.Transaccion;
import com.mx.Validadora.Util.Sha;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/V")
@CrossOrigin
public class ValidadorController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${receiver.url}")
    private String url;

    @PostMapping
    public ResponseEntity<?> validar(@Valid @RequestBody Transaccion req) {
        String concatenacion = req.getOperacion().trim()
                + String.format("%.2f", req.getImporte())
                + req.getCliente().trim();

        String calcular = Sha.sha512Hex(concatenacion);

        if (!calcular.equalsIgnoreCase(req.getSha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of( "SHA inválido", "verifica los valores ingresados"));
        }

        HttpHeaders contenido = new HttpHeaders();
        contenido.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> cuerpo = Map.of(
                "operacion", req.getOperacion(),
                "importe", req.getImporte(),
                "cliente", req.getCliente()
        );

        HttpEntity<Map<String, Object>> entidad = new HttpEntity<>(cuerpo, contenido);

        try {
            ResponseEntity<Transaccion> resp = restTemplate.postForEntity(url, entidad, Transaccion.class);
            return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar los valores ");
        }
    }
}

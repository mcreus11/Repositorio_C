package com.mx.LibroPrestamo.Config; // Ajusta este paquete

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module; 

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        // 💡 PASO CRÍTICO: Registrar el módulo de Hibernate 6
        // Esto le dice a Jackson cómo manejar las entidades proxy de JPA
        mapper.registerModule(new Hibernate6Module());
        
        // También asegura que el módulo Java Time (LocalDate) esté registrado
        mapper.findAndRegisterModules(); 
        
        return mapper;
    }
}
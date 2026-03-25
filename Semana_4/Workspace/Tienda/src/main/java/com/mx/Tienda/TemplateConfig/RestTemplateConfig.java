package com.mx.Tienda.TemplateConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	@Bean
	RestTemplate restTemp() {
		return new RestTemplate();
	}

}

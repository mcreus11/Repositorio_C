package com.mx.Departamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DepartamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartamentoApplication.class, args);
	}

}

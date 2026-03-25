package com.mx.CelularApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebasController {
	 @GetMapping("/")
	    public String home() {
	        return "Hola, Mundo"; 
	    }

}

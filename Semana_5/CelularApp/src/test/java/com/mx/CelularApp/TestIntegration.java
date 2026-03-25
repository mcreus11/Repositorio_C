package com.mx.CelularApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.mx.CelularApp.Dao.ICelularDao;
import com.mx.CelularApp.Dominio.Celular;
import com.mx.CelularApp.Service.CelularServiceImp;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application.properties")
@Transactional // Rollback automatico despues de cada test
public class TestIntegration {
	
	// Fragmento de configuración de limpieza (@BeforeEach)
	@Autowired
	private CelularServiceImp service;

	@Autowired
	private ICelularDao dao;

	@BeforeEach
	void setUp() {
	    dao.deleteAll();
	}
	
	// Fragmento de prueba unitaria (probablemente en CelularServiceTest.java)
	@Test
	void testGuardardoExitoso() {
	    Celular cel = new Celular();
	    cel.setMarca("Samsung");
	    cel.setModelo("Galaxy S23");
	    cel.setRam("8gb");
	    cel.setProcesador("Snapdragon");
	    cel.setPrecio(2500);

	    service.guardar(cel);
	    
	    assertTrue(cel.getIdCelular() > 0, "El Id se genero automaticamente");

	    Celular guardado = dao.findById(cel.getIdCelular())
	        .orElseThrow(() -> new AssertionError("Deberia de existir y por lo tanto buscar"));

	    assertNotNull(guardado);
	    assertEquals("Samsung", guardado.getMarca());
	    assertEquals("Galaxy S23", guardado.getModelo());
	    assertEquals("8gb", guardado.getRam());
	    assertEquals("Snapdragon", guardado.getProcesador());
	    assertEquals(2500, guardado.getPrecio());
	}
	
	// buscar si existe
	@Test
	void testBuscarPorId() {
	    //creacion
	    Celular cel = new Celular("Xiaomi", "Redmi Notes 12", "8gb", "Mediatek", 6500);
	    //almacenar
	    service.guardar(cel);
	    int idCel = cel.getIdCelular();

	    //validar
	    Optional<Celular> encontrado = dao.findById(idCel);
	    
	    assertTrue(encontrado.isPresent(), "Deberia encontrar el id");
	    assertEquals("Xiaomi", encontrado.get().getMarca());
	    
	}
	//buscar y que el id no exista
	@Test
	void testNoExisteBuscado() {
	    Optional<Celular> noEncontrado = dao.findById(18);

	    assertFalse(noEncontrado.isPresent(), "No deberia encontrar");
	}
	
	//listar
	@Test
	void testFindAll() {
	    service.guardar(new Celular("Apple", "iPhone 15", "6gb", "a16", 20000));
	    service.guardar(new Celular("Motorola", "Gpower", "4gb", "Snapdragon", 5000));

	    List<Celular> celus = (List<Celular>) dao.findAll(); // Llama al DAO para obtener todos

	    assertEquals(2, celus.size(), "Deberian existir 2"); // Verifica el conteo
	    assertTrue(celus.stream().anyMatch(c -> "Apple".equals(c.getMarca()))); // Verifica que Apple exista
	    assertTrue(celus.stream().anyMatch(c -> "Motorola".equals(c.getMarca()))); // Verifica que Motorola exista
	}

}

package com.mx.CelularApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.CelularApp.Controller.CelularController;
import com.mx.CelularApp.Dominio.Celular;
import com.mx.CelularApp.Service.CelularServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CelularController.class)
public class RestControllerTestMockito {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired 
    private ObjectMapper objectMapper;

    @MockBean
    private CelularServiceImp service;
    
    // crear objetos
    private final Celular cel1 = new Celular("Samsung", "Galaxi 2", "32", "Octa-core", 21050);
    private final Celular cel2 = new Celular("Huawei", "p30lite", "16", "octa", 1500);


    @Test
    void testCelularReturnListado() throws Exception {
        List<Celular> celus = List.of(cel1, cel2);
        when(service.mostrar()).thenReturn(celus);
        String listaJson = objectMapper.writeValueAsString(celus); 

        this.mockMvc.perform(get("/api/Cel/listar"))
            .andDo(print()) // muestra el resultado en consola
            .andExpect(status().isOk())
            .andExpect(content().json(listaJson));
        
        verify(service, times(1)).mostrar();
    }
    
    @Test
    void testGuardarCelular() throws Exception {
        Celular nuevoCelular = new Celular("Xiaomi", "Redmi 10", "64", "Quad", 5000);
        String jsonCelular = objectMapper.writeValueAsString(nuevoCelular);

        this.mockMvc.perform(post("/api/Cel/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCelular))
            .andDo(print()) 
            .andExpect(status().isOk()); 

        verify(service, times(1)).guardar(any(Celular.class));
    }
    

    @Test
    void testEditarCelular() throws Exception {
        Celular celularEditado = new Celular("Samsung", "S22", "128", "Octa", 18000);
        String jsonCelular = objectMapper.writeValueAsString(celularEditado);

        this.mockMvc.perform(put("/api/Cel/editar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCelular))
            .andDo(print()) 
            .andExpect(status().isOk());

        verify(service, times(1)).editar(any(Celular.class));
    }
    

    
    @Test
    void testEliminarCelular() throws Exception {
        Celular celularAEliminar = new Celular();
        celularAEliminar.setIdCelular(2); 
        String jsonCelular = objectMapper.writeValueAsString(celularAEliminar);

        this.mockMvc.perform(delete("/api/Cel/eliminar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCelular))
            .andDo(print()) 
            .andExpect(status().isOk());

        verify(service, times(1)).eliminar(any(Celular.class));
    }


    @Test
    void testBuscarCelular() throws Exception {
        Celular celularABuscar = new Celular();
        celularABuscar.setIdCelular(2);
        when(service.buscar(any(Celular.class))).thenReturn(cel2);
        String jsonCelular = objectMapper.writeValueAsString(celularABuscar);

        this.mockMvc.perform(post("/api/Cel/buscar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCelular))
            .andDo(print()) 
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(cel2))); 
            
        verify(service, times(1)).buscar(any(Celular.class));
    }

    @Test
    void testBuscarCelularPorMarca() throws Exception {
        List<Celular> samsungList = List.of(cel1);
        when(service.buscarPorMarca("Samsung")).thenReturn(samsungList);

        this.mockMvc.perform(get("/api/Cel/buscarPorMarca")
                .param("marca", "Samsung")) 
            .andDo(print()) 
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(objectMapper.writeValueAsString(samsungList)));
            
        verify(service, times(1)).buscarPorMarca("Samsung");
    }
}
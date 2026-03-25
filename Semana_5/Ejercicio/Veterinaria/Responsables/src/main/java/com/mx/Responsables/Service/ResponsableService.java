package com.mx.Responsables.Service;

import java.util.List;
import java.util.Map;

import com.mx.Responsables.Entity.Responsable;

public interface ResponsableService {
	List<Responsable> obtenerTodos();
    Responsable obtenerPorId(Long id);
    Responsable guardar(Responsable responsable);
    Responsable actualizar(Long id, Responsable responsable);
    void eliminar(Long id);
    Map<String, Object> getResponsableConVeterinaria(Long id);


}

package com.mx.Empleado.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Empleado.Entity.Empleado;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Integer>{
	public List<Empleado> findByTiendaId(int tiendaId);

	public Empleado findByNombreAndApp(String nombre, String app);

	public Empleado findByContacto(long contacto);
}

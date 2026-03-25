package com.mx.Curso.Service;

import java.util.List;

import com.mx.Curso.Dominio.CursoRol;

public interface IRolService {
	public List<CursoRol> getAllRoles();
    public CursoRol saveRol(CursoRol rol);
    public CursoRol updateRol(int id, CursoRol rolDetails);
    public void deleteRol(int id);
}

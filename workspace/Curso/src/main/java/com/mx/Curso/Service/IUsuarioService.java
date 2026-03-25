package com.mx.Curso.Service;

import java.util.List;

import com.mx.Curso.Dominio.CursoUsuario;

public interface IUsuarioService {
	public List<CursoUsuario> getAllUsuarios();
    public CursoUsuario saveUsuario(CursoUsuario usuario);
    public CursoUsuario updateUsuario(int id, CursoUsuario usuarioDetails);
    public void deleteUsuario(int id);

}

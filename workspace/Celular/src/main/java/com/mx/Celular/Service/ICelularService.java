package com.mx.Celular.Service;

import java.util.List;
import com.mx.Celular.Dominio.Celular;

public interface ICelularService {

    public void guardar(Celular celular);
    public void editar(Celular celular);
    public Celular buscar(Celular celular);
    public void eliminar(Celular celular);
    public List<Celular> mostrar();
}

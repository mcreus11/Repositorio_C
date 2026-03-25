package com.mx.Computadora.Service;

import java.util.List;
import com.mx.Computadora.Dominio.Computadora;

public interface IComputadoraService {
    public String guardar(Computadora computadora);
    public String editar(Computadora computadora);
    public String eliminar(Computadora computadora);
    public Computadora buscar(Computadora computadora);
    public List<Computadora> mostrar();
}
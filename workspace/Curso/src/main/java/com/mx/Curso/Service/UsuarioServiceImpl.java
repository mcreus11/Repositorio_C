package com.mx.Curso.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Curso.Dao.CursoUsuarioDao;
import com.mx.Curso.Dominio.CursoUsuario; 

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired 
    private CursoUsuarioDao dao; 

    @Override
    public List<CursoUsuario> getAllUsuarios() {
        return dao.findAll();
    }

    @Override
    public CursoUsuario saveUsuario(CursoUsuario usuario) {
        
        if (dao.findByNombreAndAppAndApm(usuario.getNombre(), usuario.getApp(), usuario.getApm()).isPresent()) {
            throw new RuntimeException("El empleado ya existe en la base de datos");
        }

        String regexLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

        if (!usuario.getNombre().matches(regexLetras) || !usuario.getApp().matches(regexLetras)) {
            if (usuario.getApm() != null && !usuario.getApm().matches(regexLetras)) {
                 throw new RuntimeException("Nombres y apellidos solo pueden contener caracteres de la A a la Z.");
            }
            if (!usuario.getNombre().matches(regexLetras) || !usuario.getApp().matches(regexLetras)) {
                throw new RuntimeException("Nombres y apellidos solo pueden contener caracteres de la A a la Z.");
            }
        }
        
        LocalDate fechaNacimiento = usuario.getFechaNacimiento(); 
        if (fechaNacimiento == null) {
             throw new RuntimeException("La fecha de nacimiento es obligatoria.");
        }
        
        long edad = ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
        if (edad < 18) {
            throw new RuntimeException("Solo se pueden registrar usuarios mayores a 18 años.");
        }

        String nombreLimpio = usuario.getNombre().toLowerCase().trim().replaceAll("\\s+", "");
        String appLimpio = usuario.getApp().toLowerCase().trim().replaceAll("\\s+", "");
        String correoGenerado = nombreLimpio + "." + appLimpio + "@enucom.com.mx";

        usuario.setCorreo(correoGenerado); 

        return dao.save(usuario);
    }

    @Override
    public CursoUsuario updateUsuario(int id, CursoUsuario usuarioDetails) {
        return dao.findById(id).map(usuarioExistente -> { 
            
            LocalDate fechaNacimiento = usuarioDetails.getFechaNacimiento();
            if (fechaNacimiento == null) {
                throw new RuntimeException("La fecha de nacimiento es obligatoria");
            }
            long edad = ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
            if (edad < 18) {
                throw new RuntimeException("El usuario debe ser mayor de 18 años para la actualización");
            }
            usuarioExistente.setNombre(usuarioDetails.getNombre());
            usuarioExistente.setApp(usuarioDetails.getApp());
            usuarioExistente.setApm(usuarioDetails.getApm());
            usuarioExistente.setSexo(usuarioDetails.getSexo());
            usuarioExistente.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
            usuarioExistente.setRol(usuarioDetails.getRol()); 
            
            return dao.save(usuarioExistente);
        }).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public void deleteUsuario(int id) {
        dao.deleteById(id);
    }
}
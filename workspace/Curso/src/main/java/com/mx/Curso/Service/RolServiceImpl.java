package com.mx.Curso.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Curso.Dao.CursoRolDao;
import com.mx.Curso.Dominio.CursoRol; 

@Service
public class RolServiceImpl implements IRolService {

	@Autowired 
    private CursoRolDao dao; 

    @Override
    public List<CursoRol> getAllRoles() {
        return dao.findAll();
    }

    @Override
    public CursoRol saveRol(CursoRol rol) {
        if (dao.findByPrivilegio(rol.getPrivilegio()).isPresent()) {
            throw new RuntimeException("Alerta: El rol o privilegio ya existe");
        }
        if (rol.getPrivilegio() == null || rol.getPrivilegio().trim().isEmpty()) {
            throw new RuntimeException("El privilegio o nombre del rol es obligatorio.");
        }

        return dao.save(rol);
    }

    @Override
    public CursoRol updateRol(int id, CursoRol rolDetails) {
        return dao.findById(id).map(rolExistente -> {
            
            if (!rolExistente.getPrivilegio().equalsIgnoreCase(rolDetails.getPrivilegio())) {
                if (dao.findByPrivilegio(rolDetails.getPrivilegio()).isPresent()) {
                     throw new RuntimeException("El nuevo privilegio ya está registrado");
                }
                 if (rolDetails.getPrivilegio() == null || rolDetails.getPrivilegio().trim().isEmpty()) {
                    throw new RuntimeException("El privilegio o nombre del rol es obligatorio.");
                }
            }

            rolExistente.setPrivilegio(rolDetails.getPrivilegio());
            
            return dao.save(rolExistente);
        }).orElseThrow(() -> new NoSuchElementException("Rol no encontrado con ID: " + id));
    }

    @Override
    public void deleteRol(int id) {
        dao.deleteById(id);
    }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../Entidades/Usuario';
import { Observable } from 'rxjs';
import { Rol } from '../Entidades/Rol';

@Injectable({
  providedIn: 'root',
})
export class WS {
  private baseUrl = 'http://localhost:8000/api'; 

  constructor(private http: HttpClient) { }


  // listar usuarios
  getAllUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.baseUrl}/usuarios`);
  }

  // Guardar usuario
  saveUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.baseUrl}/usuarios`, usuario);
  }

  // Actualizar un usuario
  updateUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.baseUrl}/usuarios/${usuario.idUsuario}`, usuario);
  }

  // Eliminar usuario
  deleteUsuario(idUsuario: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/usuarios/${idUsuario}`);
  }


  // listar roles
  getAllRoles(): Observable<Rol[]> {
    return this.http.get<Rol[]>(`${this.baseUrl}/roles`);
  }

  // Guardar un rol
  saveRol(rol: Rol): Observable<Rol> {
    return this.http.post<Rol>(`${this.baseUrl}/roles`, rol);
  }

  // Eliminar rol
  deleteRol(idRol: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/roles/${idRol}`);
  }
}

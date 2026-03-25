import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../Entidades/Usuario';
import { WS } from '../../../Service/ws';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Rol } from '../../../Entidades/Rol';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './editar.html',
  styleUrls: ['./editar.css'],
})
export class Editar implements OnInit {

  usuario: Usuario = {
    idUsuario: 0,
    nombre: '',
    app: '',
    apm: '',
    sexo: '',
    fechaNacimiento: '',
    correo: '',
    rol: { idRol: 0, privilegio: '' }
  };

  roles: Rol[] = [];

  constructor(
    private service: WS,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) this.cargarUsuario(id);
    this.cargarRoles();
  }

  cargarUsuario(id: number): void {
    this.service.getAllUsuarios().subscribe({
      next: (usuarios) => {
        const encontrado = usuarios.find(u => u.idUsuario === id);
        if (encontrado) {
          this.usuario = encontrado;
        } else {
          Swal.fire('Error', 'Usuario no encontrado.', 'error');
          this.router.navigate(['/listarU']);
        }
      },
      error: () => Swal.fire('Error', 'Error al cargar usuario.', 'error')
    });
  }

  cargarRoles(): void {
    this.service.getAllRoles().subscribe({
      next: (roles) => (this.roles = roles),
      error: () => Swal.fire('Error', 'No se pudieron cargar los roles.', 'error')
    });
  }

  actualizarUsuario(): void {
    if (!this.usuario.nombre || !this.usuario.app || !this.usuario.sexo || !this.usuario.fechaNacimiento) {
      Swal.fire('Atención', 'Todos los campos obligatorios deben completarse.', 'warning');
      return;
    }

    Swal.fire({
      title: '¿Deseas guardar los cambios?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Sí, actualizar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.updateUsuario(this.usuario).subscribe({
          next: () => {
            Swal.fire('Actualizado', 'Usuario actualizado correctamente.', 'success');
            setTimeout(() => this.router.navigate(['/listarU']), 2000);
          },
          error: (error) => {
            console.error('Error al actualizar usuario:', error);
            const errorMsg = error.error?.message || 'Error desconocido al actualizar.';
            Swal.fire('Error', errorMsg, 'error');
          }
        });
      }
    });
  }
}

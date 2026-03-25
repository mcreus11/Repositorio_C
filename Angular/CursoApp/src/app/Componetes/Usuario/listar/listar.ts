import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../Entidades/Usuario';
import { WS } from '../../../Service/ws';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar',
  imports: [CommonModule],
  templateUrl: './listar.html',
  styleUrls: ['./listar.css'],
})
export class Listar implements OnInit {

  usuarios: Usuario[] = [];

  constructor(private wsService: WS, private router: Router) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.wsService.getAllUsuarios().subscribe({
      next: (data) => {
        this.usuarios = data;
        console.log('Usuarios cargados:', data);
      },
      error: (error) => {
        console.error('Error al cargar usuarios:', error);
        Swal.fire('Error', 'No se pudo cargar la lista de usuarios.', 'error');
      },
    });
  }

  editarUsuario(idUsuario: number): void {
    this.router.navigate(['/editarU', idUsuario]);
  }

  eliminarUsuario(idUsuario: number): void {
    Swal.fire({
      title: '¿Deseas eliminar este usuario?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
    }).then((result) => {
      if (result.isConfirmed) {
        this.wsService.deleteUsuario(idUsuario).subscribe({
          next: () => {
            Swal.fire('Eliminado', 'El usuario ha sido eliminado con éxito.', 'success');
            this.cargarUsuarios();
          },
          error: (error) => {
            console.error('Error al eliminar:', error);
            Swal.fire('Error', 'No se pudo eliminar el usuario.', 'error');
          },
        });
      }
    });
  }
}

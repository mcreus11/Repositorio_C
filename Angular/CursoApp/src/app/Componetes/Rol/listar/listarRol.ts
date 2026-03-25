import { Component, OnInit } from '@angular/core';
import { WS } from '../../../Service/ws';
import { Router, RouterModule } from '@angular/router';
import { Rol } from '../../../Entidades/Rol';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-rol',
  imports: [CommonModule, RouterModule],
  templateUrl: './listarRol.html',
  styleUrl: './listar.css',
})
export class ListarRol implements OnInit {

  roles: Rol[] = [];

  constructor(private service: WS, private router: Router) {}

  ngOnInit(): void {
    this.cargarRoles();
  }

  cargarRoles(): void {
    this.service.getAllRoles().subscribe({
      next: (data) => (this.roles = data),
      error: (error) => {
        console.error('Error al listar roles:', error);
        Swal.fire('Error', 'No se pudieron cargar los roles.', 'error');
      }
    });
  }

  eliminarRol(idRol: number): void {
    Swal.fire({
      title: '¿Eliminar rol?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.deleteRol(idRol).subscribe({
          next: () => {
            Swal.fire('Eliminado', 'El rol fue eliminado correctamente.', 'success');
            this.cargarRoles();
          },
          error: (err) => {
            console.error('Error al eliminar rol:', err);
            Swal.fire('Error', 'No se pudo eliminar el rol.', 'error');
          }
        });
      }
    });
  }

  editarRol(idRol: number): void {
    this.router.navigate(['/editarR', idRol]);
  }

  irAGuardar(): void {
    this.router.navigate(['/guardarR']);
  }
}

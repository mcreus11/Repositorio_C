import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { WS } from '../../../Service/ws';
import { Rol } from '../../../Entidades/Rol';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-rol',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './editarRol.html',
  styleUrl: './editar.css',
})
export class EditarRol implements OnInit {
  rol: Rol = { idRol: 0, privilegio: '' };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: WS
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) this.cargarRol(id);
  }

  cargarRol(idRol: number): void {
    this.service.getAllRoles().subscribe({
      next: (roles) => {
        const encontrado = roles.find(r => r.idRol === idRol);
        if (encontrado) {
          this.rol = { ...encontrado };
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Rol no encontrado',
            text: 'No se encontró el rol solicitado.',
          });
          this.router.navigate(['/listarR']);
        }
      },
      error: () => {
        Swal.fire({
          icon: 'error',
          title: 'Error al cargar',
          text: 'No se pudieron obtener los roles del servidor.',
        });
      }
    });
  }

  actualizarRol(): void {
    if (!this.rol.privilegio.trim()) {
      Swal.fire({
        icon: 'warning',
        title: 'Campo vacío',
        text: 'El privilegio no puede estar vacío.',
      });
      return;
    }

    this.service.saveRol(this.rol).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Rol actualizado',
          text: 'Los cambios fueron guardados correctamente.',
          showConfirmButton: false,
          timer: 1500
        }).then(() => this.router.navigate(['/listarR']));
      },
      error: (err) => {
        console.error('Error al actualizar rol:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error al actualizar',
          text: 'No se pudo actualizar el rol. Intenta nuevamente.',
        });
      }
    });
  }
}

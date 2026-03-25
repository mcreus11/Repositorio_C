import { Component } from '@angular/core';
import { Rol } from '../../../Entidades/Rol';
import { WS } from '../../../Service/ws';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-rol',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './guardarRol.html',
  styleUrl: './guardar.css',
})
export class GuardarRol {
  rol: Rol = { idRol: 0, privilegio: '' };

  constructor(private service: WS, private router: Router) {}

  guardarRol(): void {
    if (!this.rol.privilegio.trim()) {
      Swal.fire({
        icon: 'warning',
        title: 'Campo vacío',
        text: 'El privilegio no puede estar vacío.',
        confirmButtonColor: '#3085d6'
      });
      return;
    }

    this.service.saveRol(this.rol).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Rol guardado',
          text: 'El rol fue registrado correctamente.',
          showConfirmButton: false,
          timer: 1800
        }).then(() => {
          this.router.navigate(['/listarR']);
        });
      },
      error: (err) => {
        console.error('Error al guardar rol:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error al guardar',
          text: 'Ya existe este rol en los registros. Verifica los datos.',
        });
      }
    });
  }
}

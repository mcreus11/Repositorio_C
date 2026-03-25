import { Component } from '@angular/core';
import { WS } from '../../../Service/ws';
import { Router, RouterModule } from '@angular/router';
import { Usuario } from '../../../Entidades/Usuario';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2'; 

@Component({
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-guardar',
  templateUrl: './guardar.html',
  styleUrls: ['./guardar.css']
})
export class Guardar {

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

  constructor(private service: WS, private router: Router) {}

  guardarUsuario(): void {
    //  Validación de campos vacíos
    if (!this.usuario.nombre || !this.usuario.app || !this.usuario.sexo || !this.usuario.fechaNacimiento) {
      Swal.fire({
        icon: 'warning',
        title: 'Campos requeridos',
        text: 'Todos los campos obligatorios deben llenarse.',
        confirmButtonColor: '#3085d6'
      });
      return;
    }

    //  Validar edad
    const edad = this.calcularEdad(this.usuario.fechaNacimiento);
    if (edad < 18) {
      Swal.fire({
        icon: 'error',
        title: 'Edad inválida',
        text: 'El usuario debe ser mayor de 18 años.',
        confirmButtonColor: '#d33'
      });
      return;
    }

    //  Generar correo automáticamente
    this.usuario.correo = `${this.usuario.nombre.toLowerCase()}.${this.usuario.app.toLowerCase()}@enucom.com.mx`;

    this.usuario.rol = { idRol: 1, privilegio: 'ADMINISTRADOR' };

    console.log(' Enviando al backend:', this.usuario);

    this.service.saveUsuario(this.usuario).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Usuario guardado',
          text: 'El usuario se guardó correctamente.',
          confirmButtonColor: '#3085d6',
          timer: 2000,
          timerProgressBar: true
        }).then(() => {
          this.router.navigate(['/listarU']);
        });
      },
      error: (error) => {
        console.error('Error al guardar usuario:', error);
        const errorMsg = error.error?.message || 'Error desconocido al guardar usuario.';
        Swal.fire({
          icon: 'error',
          title: 'Error de Backend',
          text: errorMsg,
          confirmButtonColor: '#d33'
        });
      }
    });
  }

  //  Calcular edad desde fecha de nacimiento
  private calcularEdad(fechaNac: string): number {
    const hoy = new Date();
    const nacimiento = new Date(fechaNac);
    let edad = hoy.getFullYear() - nacimiento.getFullYear();
    const mes = hoy.getMonth() - nacimiento.getMonth();
    if (mes < 0 || (mes === 0 && hoy.getDate() < nacimiento.getDate())) {
      edad--;
    }
    return edad;
  }
}

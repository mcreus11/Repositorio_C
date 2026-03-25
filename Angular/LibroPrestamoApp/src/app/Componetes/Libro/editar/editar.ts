import { Component, OnInit } from '@angular/core';
import { WS } from '../../../Service/ws';
import { Libro } from '../../../Entidades/Libro';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editar',
  imports: [FormsModule],
  templateUrl: './editar.html',
  styleUrl: './editar.css',
})
export class Editar implements OnInit {
  libro: Libro = new Libro();

  constructor(private service: WS, private router: Router) { }

  ngOnInit(): void {
    this.cargarLibroParaEditar();
  }

  cargarLibroParaEditar(): void {
    // 1. Recuperar el ID del localStorage (donde lo guardó el componente Listar)
    const idString = localStorage.getItem('id');
    
    if (idString) {
      const idLibro = parseInt(idString);
      // 2. Llamar al servicio para obtener los datos del libro
      this.service.obtenerLibroPorId(idLibro).subscribe(data => {
        this.libro = data;
        // 3. Limpiar el localStorage
        localStorage.removeItem('id');
      });
    } else {
      // Si no hay ID, redirigir al listado
      this.router.navigate(['listarLibro']);
    }
  }

  actualizar() {
    this.service.editarLibro(this.libro).subscribe(data => {
      Swal.fire({
        icon: "success",
        title: "Actualizado",
        text: JSON.stringify(data),
        showConfirmButton: false,
        timer: 2100
      });
      // Navegar de vuelta al listado después de actualizar
      this.router.navigate(['listarLibro']);
    });
  }
}
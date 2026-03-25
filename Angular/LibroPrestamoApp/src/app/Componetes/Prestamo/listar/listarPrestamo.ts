import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Prestamo } from '../../../Entidades/Prestamo';
import { Router } from '@angular/router';
import { WS } from '../../../Service/ws';

@Component({
  selector: 'app-listar',
  imports: [],
  templateUrl: './listarPrestamo.html',
  styleUrl: './listar.css',
})
export class ListarPrestamo implements OnInit {
  
  prestamos!: Prestamo[];

  constructor(private service: WS, private router: Router) { }

  ngOnInit(): void {
    this.listarPrestamos();
  }

  listarPrestamos() {
    this.service.listarPrestamos().subscribe(data => {
      this.prestamos = data;
    });
  }
  
  // --- FUNCIONES DE ACCIÓN ---

  nuevoPrestamo() {
    this.router.navigate(['guardarPrestamo']); 
  }

  editar(prestamo: Prestamo): void {
    // Guardamos el ID en localStorage y navegamos al componente de edición
    localStorage.setItem('idPrestamo', prestamo.idPrestamo.toString());
    this.router.navigate(['editarPrestamo']);
  }

  eliminar(prestamo: Prestamo): void {
    Swal.fire({
      title: "¿Eliminar este préstamo?",
      text: "Esta acción no se puede deshacer.",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar"
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.eliminarPrestamo(prestamo.idPrestamo).subscribe({
          next: () => {
            this.listarPrestamos(); // Recargar la lista
            Swal.fire('¡Eliminado!', 'El préstamo ha sido eliminado.', 'success');
          },
          error: (err) => {
             Swal.fire('Error', 'No se pudo eliminar el préstamo. Error de servidor.', 'error');
             console.error(err);
          }
        });
      }
    });
  }
}

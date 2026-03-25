import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Ws } from '../../Service/ws';
import { Celular } from '../../Entidad/Celular';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-celular',
  standalone: true, // Asumo que usas standalone components en Angular moderno
  imports: [FormsModule],
  templateUrl: './editar-celular.html',
  styleUrl: './editar-celular.css',
})
export class EditarCelular implements OnInit {

  // Aseguramos que 'cel' está inicializado
  cel: Celular = new Celular(); 
  isLoaded: boolean = false; // Bandera para indicar que la carga del celular está completa

  constructor(private service: Ws, private router: Router) {}

  ngOnInit(): void {
    this.buscarCel();
  }

  buscarCel(){
    const celuString = localStorage.getItem('id');
    
    // Convertimos a número. Si celuString es null, Number(null) es 0.
    const idToSearch = Number(celuString); 

    // ⭐ VALIDACIÓN ESTRICTA: Asegura que el ID existe y es positivo
    if (idToSearch > 0) { 
      this.cel.idCelular = idToSearch;
      
      console.log("DEBUG: Buscando celular con ID:", this.cel.idCelular);

      // Llamada al servicio para obtener los datos del celular
      this.service.buscarCelu(this.cel).subscribe(
        data =>{
          // Si el backend devuelve 200 OK con datos (data no es null, undefined, o {})
          if (data && data.idCelular) { 
             this.cel = data;
             this.isLoaded = true; // La carga fue exitosa
             console.log("DEBUG: Celular cargado exitosamente.", data);
          } else {
             // Esto se activa si el backend devuelve un 200 OK con cuerpo null o vacío
             console.error(`ERROR: La API no devolvió datos para el ID ${this.cel.idCelular}. (Revisar el Backend)`);
             Swal.fire('Error', 'No se pudo cargar el celular. El ID es válido pero no se encontraron datos.', 'error');
             this.router.navigate(['listarC']); 
          }
        },
        error => {
          // Si el servidor responde con 400, 500, o 404 (si el backend lo maneja)
          console.error('ERROR COMPLETO AL BUSCAR EL CELULAR:', error);
          Swal.fire('Error', 'Error de conexión o API al buscar el celular.', 'error');
          this.router.navigate(['listarC']); 
        }
      );
    } else {
      // Si no hay ID en localStorage, redirigimos inmediatamente
      console.error("ERROR: No se encontró un ID válido en localStorage. Redirigiendo.");
      Swal.fire('Error', 'Acceso denegado. No se especificó qué celular editar.', 'error');
      this.router.navigate(['listarC']); 
    }
  }

  editarC() {
    // ⭐ Validación básica antes de enviar (para evitar el 400 si falta el ID)
    if (!this.cel.idCelular || this.cel.idCelular <= 0) {
        Swal.fire('Error', 'No se puede editar, el ID del celular es inválido.', 'error');
        return;
    }
    
    // ⭐ Implementación del manejo de errores
    this.service.editarCelu(this.cel).subscribe({
      next: () => {
        Swal.fire({
          icon: "success",
          title: "¡Éxito!",
          text: `El Celular ${this.cel.marca} se editó con éxito.`,
          showConfirmButton: false,
          timer: 2000
        });
        this.router.navigate(['listarC']);
      },
      error: (err) => {
        console.error("Error completo de la API al editar:", err);
        
        // Mensaje específico para el 400 Bad Request
        const errorMessage = err.status === 400 
            ? "Error de validación: Asegúrate de que todos los campos son correctos (ej. precio no vacío)." 
            : `Error al editar el celular (Status: ${err.status}).`;

        Swal.fire({
          icon: "error",
          title: "Error de Edición",
          text: errorMessage
        });
      }
    });
  }
}

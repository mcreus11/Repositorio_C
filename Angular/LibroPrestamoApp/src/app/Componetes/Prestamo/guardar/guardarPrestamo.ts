import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Libro } from '../../../Entidades/Libro';
import { Prestamo } from '../../../Entidades/Prestamo';
import { WS } from '../../../Service/ws';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-guardar',
  imports: [FormsModule],
  templateUrl: './guardarPrestamo.html',
  styleUrl: './guardar.css',
})
export class GuardarPrestamo implements OnInit {
  
  // Objeto para almacenar el nuevo préstamo (vacío)
  prestamo: Prestamo = new Prestamo();
  
  // Array para almacenar los libros disponibles para el select
  libros!: Libro[]; 

  constructor(private service: WS, private router: Router) { }

  ngOnInit(): void {
    // Cargar la lista de libros al iniciar el componente
    this.cargarListaLibros();
  }

  cargarListaLibros() {
     // Usamos el método que ya debe existir en WS para listar libros
     this.service.listarLibro().subscribe(data => {
        this.libros = data;
     });
  }

  guardar() {
    // 💡 Asegúrate de que el objeto prestamo tiene el libroId seleccionado
    this.service.guardarPrestamo(this.prestamo).subscribe({
        next: (data) => {
            Swal.fire({
                icon: "success",
                title: "Préstamo Guardado",
                text: "El préstamo se ha registrado correctamente.",
                showConfirmButton: false,
                timer: 2100
            });
            // Navegar al listado después de guardar
            this.router.navigate(['ListarPrestamo']);
        },
        error: (error) => {
             Swal.fire('Error', 'No se pudo guardar el préstamo. Verifica los datos.', 'error');
             console.error(error);
        }
    });
  }
}
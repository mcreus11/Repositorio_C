import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Prestamo } from '../../../Entidades/Prestamo';
import { WS } from '../../../Service/ws';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar',
  imports: [FormsModule, CommonModule],
  templateUrl: './editarPrestamo.html',
  styleUrl: './editar.css',
})
export class EditarPrestamo implements OnInit {

    // Asegúrate de que este objeto inicialice el 'libroId' en su constructor
    prestamo: Prestamo = new Prestamo(); 

    constructor(
        private service: WS, 
        private router: Router, 
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.cargarPrestamoParaEdicion();
    }

    cargarPrestamoParaEdicion() {
        this.activatedRoute.params.subscribe(params => {
            let id = params['id']; // Asume que la ruta es /editarPrestamo/:id
            if (id) {
                // 💡 CORREGIDO: Usando el nombre del método de tu servicio
                this.service.obtenerPrestamoPorId(id).subscribe({
                    next: (data: Prestamo) => {
                        this.prestamo = data;
                        
                        // 💡 TRATAMIENTO DE FECHAS (Convierte de objeto Date a string YYYY-MM-DD)
                        // 💡 CORRECCIÓN: Agregamos 'as any' para resolver el error de tipado.
                        if (this.prestamo.fechaInicio) {
                            this.prestamo.fechaInicio = this.formatDate(this.prestamo.fechaInicio) as any;
                        }
                        if (this.prestamo.fechaFin) {
                            this.prestamo.fechaFin = this.formatDate(this.prestamo.fechaFin) as any;
                        }
                    },
                    error: (error) => {
                        console.error("Error al cargar el préstamo:", error);
                        Swal.fire({
                            icon: "error",
                            title: "Error de Carga",
                            text: "No se pudo cargar el préstamo con ID " + id
                        });
                        // Opcional: Redirigir si falla la carga
                        // this.router.navigate(['/listarPrestamo']);
                    }
                });
            }
        });
    }
    
    // Método auxiliar para formatear la fecha a YYYY-MM-DD
    formatDate(date: any): string {
        // Manejar el caso donde date podría ser una cadena ISO (del backend) o un objeto Date
        const d = new Date(date);
        let month = '' + (d.getMonth() + 1);
        let day = '' + d.getDate();
        const year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }

    editar() {
        // ESTE MÉTODO SE LLAMA AL ENVIAR EL FORMULARIO
        this.service.editarPrestamo(this.prestamo).subscribe({
            next: (data) => {
                Swal.fire({
                    icon: "success",
                    title: "Editado",
                    text: data,
                    showConfirmButton: false,
                    timer: 2100
                });
                this.router.navigate(['/listarPrestamo']);
            },
            error: (error) => {
                 // Usa error.error para obtener el cuerpo de respuesta de error del backend
                Swal.fire({
                    icon: "error",
                    title: "Error al Editar",
                    text: error.error || 'Ocurrió un error desconocido' 
                });
            }
        });
    }
}

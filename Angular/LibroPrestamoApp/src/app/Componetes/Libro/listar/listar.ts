import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Libro } from '../../../Entidades/Libro';
import { WS } from '../../../Service/ws';
import { Router, RouterLink } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar',
  imports: [RouterLink],
  templateUrl: './listar.html',
  styleUrl: './listar.css',
})
export class Listar implements OnInit{
  constructor(private service : WS, private router : Router){}

  librito : Libro = new Libro();
  libros !: Libro[];

  ngOnInit(): void {
    this.listarLibros();
  }

  listarLibros(){
    this.service.listarLibro().subscribe(data=>{
      this.libros = data
    })
  }
  
  nuevoLibro() {
    this.router.navigate(['guardarlibro']);
  }
  editar(libro: Libro): void {
    localStorage.setItem('id', libro.idLibro.toString());
    this.router.navigate(['editarLibro']);
  }

  eliminar(libro: Libro): void {
    Swal.fire({
      title: "¿Estás seguro de eliminar este libro?",
      text: "¡No podrás revertir esto!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "Sí, eliminarlo",
      cancelButtonText: "Cancelar"
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.eliminarLibro(libro.idLibro).subscribe({
          next: (data) => {
            // Recarga la lista después de la eliminación exitosa
            this.listarLibros(); 
            Swal.fire('¡Eliminado!', 'El libro ha sido eliminado.', 'success');
          },
          error: (error) => {
            Swal.fire('Error', 'No se pudo eliminar el libro.', 'error');
            console.error(error);
          }
        });
      }
    });
  }

}

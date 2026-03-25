import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Celular } from '../../Entidad/Celular';
import { Ws } from '../../Service/ws';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-listar-celular',
  imports: [FormsModule],
  templateUrl: './listar-celular.html',
  styleUrl: './listar-celular.css',
})
export class ListarCelular implements OnInit {

  //intanciar la entidad
  celu: Celular = new Celular;

  celulares: Celular[] = [];
    marcaBusqueda: string = ''; 


  constructor(private router: Router, private service: Ws) { }
  ngOnInit(): void {
    this.listarCelulares();

  }

  listarCelulares() {
    this.service.listarCelu().subscribe(data => {
      this.celulares = data;
    });
  }

// metodo para redireccionar al componentes de editar
editarButton(celu : Celular){
    localStorage.setItem('id', celu.idCelular.toString());
    this.router.navigate(['editarC']);
}

btnEliminar(celu: Celular) {
    Swal.fire({
      title: "Quieres eliminar este registro?",
      text: "No se pueden revertir los cambios!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, eliminar!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.eliminarC(celu).subscribe(data => {

        })
        Swal.fire({
          title: "Eliminado!",
          text: "El registro ha sido eliminado.",
          icon: "success"
        });
        this.listarCelulares();
      } else if (result.isDismissed) {
        Swal.fire({
          title: "ELIMINAR",
          text: "ELIMINACION CANCELADA",
          icon: "info",
          confirmButtonText: "OK"
        })
      }
    });
  }

   buscarPorMarca() {
    const marca = this.marcaBusqueda.trim();
    
    if (!marca) {
      this.listarCelulares();
      return;
    }
    
    this.service.buscarPorMarca(marca).subscribe({
      next: (data) => {
        this.celulares = data;
        if (data.length === 0) {
            Swal.fire(`No se encontraron celulares con la marca "${marca}"`, 'info');
        }
      },
      error: (err) => {
        console.error('Error al buscar por marca:', err);
        Swal.fire('Error', 'Ocurrió un error al buscar la marca. Verifique la API.', 'error');
      }
    });
  }

}

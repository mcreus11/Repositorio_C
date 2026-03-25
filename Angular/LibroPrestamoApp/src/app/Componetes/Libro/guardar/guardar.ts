import { Component } from '@angular/core';
import { Libro } from '../../../Entidades/Libro';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { WS } from '../../../Service/ws';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar',
  imports: [FormsModule],
  templateUrl: './guardar.html',
  styleUrl: './guardar.css',
})
export class Guardar {
 libro: Libro = new Libro();

constructor(private service: WS, private router: Router) { }

guardar() {
  this.service.guardarLibro(this.libro).subscribe(data => {
    Swal.fire({
      icon: "success",
      title: "Guardar",
      text: JSON.stringify(data),
      showConfirmButton: false,
      timer: 2100
    });
    this.router.navigate(['listarLibro'])
  })
}

}

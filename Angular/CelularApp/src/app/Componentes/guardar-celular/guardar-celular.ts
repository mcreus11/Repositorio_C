import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Celular } from '../../Entidad/Celular';
import { Ws } from '../../Service/ws';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-celular',
  imports: [FormsModule],
  templateUrl: './guardar-celular.html',
  styleUrl: './guardar-celular.css',
})
export class GuardarCelular {
  cel: Celular = new Celular();
  constructor(private service: Ws, private router: Router) { }

guardar() {
    this.service.guardarCelu(this.cel).subscribe(data => {
        Swal.fire({
            icon: "success",
            title: "Celular registrado",
            showConfirmButton: false,
            timer: 1500
        });
        this.router.navigate(['listarC'])
    });
}

}

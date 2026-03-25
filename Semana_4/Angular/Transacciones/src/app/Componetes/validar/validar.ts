import { Component } from '@angular/core';
import { Validador } from '../../Entidades/Validador';
import { WS } from '../../Service/ws';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-validar',
  imports: [FormsModule, CommonModule, ],
  templateUrl: './validar.html',
  styleUrl: './validar.css',
})
export class Validar {
   operacion = '';
  importe = '';
  cliente = '';
  shaGenerado = '';

  constructor(private ws: WS) {}

  async generarSHA(): Promise<string> {
  const texto = `${this.operacion}${parseFloat(this.importe).toFixed(2)}${this.cliente}`;

  const encoder = new TextEncoder();
  const data = encoder.encode(texto);
  const hashBuffer = await crypto.subtle.digest('SHA-512', data);
  const hashArray = Array.from(new Uint8Array(hashBuffer));
  const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
  this.shaGenerado = hashHex;

  return hashHex;
}


  async enviar() {
    if (!this.operacion || !this.importe || !this.cliente) {
      Swal.fire('Campos incompletos', 'Por favor llena todos los campos', 'warning');
      return;
    }

    const sha = await this.generarSHA();
    const validador: Validador = {
      operacion: this.operacion,
      importe: this.importe,
      cliente: this.cliente,
      sha: sha
    };

    this.ws.validarTransaccion(validador).subscribe({
      next: (resp) => {
        Swal.fire('Éxito', 'Transacción validada: ' , 'success');
      },
      error: (err) => {
        Swal.fire('Error', 'Verifica los valores ingresados ' , 'error');
      }
    });
  }
}
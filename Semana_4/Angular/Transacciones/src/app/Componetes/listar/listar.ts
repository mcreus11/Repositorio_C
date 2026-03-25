import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';
import { WS } from '../../Service/ws';
import { Transaccion } from '../../Entidades/Transaccion';

@Component({
  selector: 'app-listar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listar.html',
  styleUrls: ['./listar.css']
})
export class Listar implements OnInit {

  transacciones: Transaccion[] = [];

  constructor(private ws: WS) {}

  ngOnInit(): void {
    this.cargarTransacciones();
  }

  cargarTransacciones(): void {
    this.ws.listar().subscribe({
      next: (data) => this.transacciones = data,
      error: () => Swal.fire('Error', 'No se pudieron cargar las transacciones', 'error')
    });
  }

  cancelarTransaccion(id: number, referencia: string | undefined) {
  if (!referencia) {
    Swal.fire('Error', 'Referencia no disponible para esta transacción', 'error');
    return;
  }

  Swal.fire({
    title: '¿Cancelar transacción?',
    text: 'Esta acción no se puede deshacer',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Sí, cancelar',
    cancelButtonText: 'No',
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6'
  }).then((result) => {
    if (result.isConfirmed) {
      this.ws.patchEstatus(id, referencia, 'Cancelada').subscribe({
  next: (resp) => {
    Swal.fire({
      icon: 'success',
      title: 'Cancelada',
      text: 'La transacción fue cancelada correctamente',
      timer: 1500,
      showConfirmButton: false,
      position: 'top-end'
    });
    setTimeout(() => this.cargarTransacciones(), 400);
  },
  error: (err) => {
    Swal.fire('Error', 'No se pudo cancelar la transacción', 'error');
  }
});

    }
  });
}
}
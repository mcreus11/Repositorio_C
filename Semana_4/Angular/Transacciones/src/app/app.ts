import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,  
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css'] 
})
export class App {
  protected readonly title = signal('Transacciones');
  constructor(private router: Router) {}

  irAValidar() {
    this.router.navigate(['/validar']);
  }

  irAListar() {
    this.router.navigate(['/listar']);
  }
}

import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLinkWithHref],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CelularApp');

  constructor(private router : Router){}

listarCelular(){
    this.router.navigate(['listarC']);
}

guardarCel(){
    this.router.navigate(['guardarC']);
}
}

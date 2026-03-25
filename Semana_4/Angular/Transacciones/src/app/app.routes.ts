import { Routes } from '@angular/router';
import { Validar } from './Componetes/validar/validar';
import { Listar } from './Componetes/listar/listar';

export const routes: Routes = [
  { path: 'validar', component: Validar },
  { path: 'listar', component: Listar }
];

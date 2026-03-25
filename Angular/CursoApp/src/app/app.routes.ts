import { Routes } from '@angular/router';
import { Listar } from './Componetes/Usuario/listar/listar';
import { Guardar } from './Componetes/Usuario/guardar/guardar';
import { Editar } from './Componetes/Usuario/editar/editar';
import { ListarRol } from './Componetes/Rol/listar/listarRol';
import { GuardarRol } from './Componetes/Rol/guardar/guardarRol';
import { EditarRol } from './Componetes/Rol/editar/editarRol';

export const routes: Routes = [
      { path: '', redirectTo: '/', pathMatch: 'full' },
    { path: 'listarU', component: Listar },
      { path: 'guardarU', component: Guardar },
{ path: 'editarU/:id', component: Editar },
 { path: 'listarR', component: ListarRol },
  { path: 'guardarR', component: GuardarRol },
  { path: 'editarR/:id', component: EditarRol }
];

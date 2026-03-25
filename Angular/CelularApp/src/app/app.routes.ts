import { Routes } from '@angular/router';
import { EditarCelular } from './Componentes/editar-celular/editar-celular';
import { GuardarCelular } from './Componentes/guardar-celular/guardar-celular';
import { ListarCelular } from './Componentes/listar-celular/listar-celular';

export const routes: Routes = [
    {
  path : 'listarC', component : ListarCelular
},
{
  path : 'guardarC', component : GuardarCelular
},
{
  path : 'editarC', component : EditarCelular
}
];

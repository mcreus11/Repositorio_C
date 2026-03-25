import { Routes } from '@angular/router';
import { Guardar } from './Componetes/Libro/guardar/guardar';
import { Listar } from './Componetes/Libro/listar/listar';
import { Editar } from './Componetes/Libro/editar/editar';
import { ListarPrestamo } from './Componetes/Prestamo/listar/listarPrestamo';
import { GuardarPrestamo } from './Componetes/Prestamo/guardar/guardarPrestamo';
import { EditarPrestamo } from './Componetes/Prestamo/editar/editarPrestamo';

export const routes: Routes = [
    {path : 'listarLibro', component : Listar},
{path : 'guardarLibro', component : Guardar},
{path : 'editarLibro', component : Editar},
{path : 'listarPrestamo', component : ListarPrestamo},
{path : 'guardarPrestamo', component : GuardarPrestamo},
{path : 'editarPrestamo', component : EditarPrestamo}


];


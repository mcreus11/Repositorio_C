import { Rol } from "./Rol";

export class Usuario {
  idUsuario!: number;
  nombre!: string;
  app!: string;
  apm!: string;
  sexo!: string;
  fechaNacimiento!: string;
  correo!: string;
  rol!: Rol;

  constructor() {
    this.idUsuario = 0;
    this.nombre = '';
    this.app = '';
    this.apm = '';
    this.sexo = '';
    this.fechaNacimiento = '';
    this.correo = '';
    this.rol = new Rol();
  }
}

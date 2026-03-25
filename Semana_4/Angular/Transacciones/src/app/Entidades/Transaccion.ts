export class Transaccion {
  id?: number;  
  operacion!: string;
  importe!: number;
  cliente!: string;
  referencia?: string;
  estatus?: string;
}


import { Libro } from './Libro'; // Asegúrate de importar Libro

export class Prestamo {
  idPrestamo!: number;
  fechaInicio!: Date;
  fechaFin!: Date;
  
  // 💡 Tipado correcto para la relación
  libroId: Libro; 

  constructor() {
    // 💡 PASO CRÍTICO: Inicializar todas las propiedades, especialmente la anidada
    this.idPrestamo = 0;
    this.fechaInicio = new Date();
    this.fechaFin = new Date();
    this.libroId = new Libro(); // <-- Inicializa el objeto anidado
  }
}
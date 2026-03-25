import { Injectable } from '@angular/core';
import { Libro } from '../Entidades/Libro';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Prestamo } from '../Entidades/Prestamo';

@Injectable({
  providedIn: 'root',
})
export class WS {
  constructor(private http : HttpClient){}

url = "http://localhost:8010";

listarLibro(){
  return this.http.get<Libro[]>(this.url + "/Libro/listar");
}

guardarLibro(libro : Libro){
  return this.http.post<String>(this.url + "/Libro/guardar", libro, { responseType : "text" as "json" });
}

eliminarLibro(id: number) {
  return this.http.delete(this.url + '/Libro/eliminar/' + id, { responseType: 'text' as 'json' }); 
}

  editarLibro(libro: Libro) {
    return this.http.put(this.url + '/Libro/editar', libro, { responseType: 'text' });
  }

  obtenerLibroPorId(id: number) {
    return this.http.get<Libro>(this.url + '/Libro/buscar/' + id);
  }


  //prestamo
  listarPrestamos() {
    return this.http.get<Prestamo[]>(this.url + '/Prestamo/listar'); 
  }

  obtenerPrestamoPorId(id: number) {
    return this.http.get<Prestamo>(this.url + '/Prestamo/buscar/' + id); 
  }

  guardarPrestamo(prestamo: Prestamo) {
    
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    
    return this.http.post(
        this.url + '/Prestamo/guardar', 
        prestamo, 
        { 
            headers: headers, 
            responseType: 'text' 
        } 
    ); 
}

  editarPrestamo(prestamo: Prestamo) {
    return this.http.put(this.url + '/Prestamo/editar', prestamo, { responseType: 'text' });
  }

  eliminarPrestamo(id: number) {
    return this.http.delete(this.url + '/Prestamo/eliminar/' + id, { responseType: 'text' as 'json' }); 
  }
  
}

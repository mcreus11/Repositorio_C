import { Injectable } from '@angular/core';
import { Validador } from '../Entidades/Validador';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaccion } from '../Entidades/Transaccion';

@Injectable({
  providedIn: 'root',
})
export class WS {

  private urlVali = 'http://localhost:8001/V';
  private urlPer = 'http://localhost:8000/T';

  constructor(private http: HttpClient) { }

  validarTransaccion(validador: Validador): Observable<Transaccion> {
    return this.http.post<Transaccion>(this.urlVali, validador);
  }

  listar(): Observable<Transaccion[]> {
    return this.http.get<Transaccion[]>(this.urlPer);
  }

  obtenerPorId(id: number): Observable<Transaccion> {
    return this.http.get<Transaccion>(`${this.urlPer}/${id}`);
  }

  guardar(tx: Transaccion): Observable<Transaccion> {
    return this.http.post<Transaccion>(this.urlPer, tx);
  }

  actualizar(id: number, tx: Transaccion): Observable<Transaccion> {
    return this.http.put<Transaccion>(`${this.urlPer}/${id}`, tx);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlPer}/${id}`);
  }

  patchEstatus(id: number, referencia: string, estatus: string): Observable<any> {
  return this.http.patch(`${this.urlPer}/${id}`, { referencia, estatus }, { responseType: 'text' });
}



  
}

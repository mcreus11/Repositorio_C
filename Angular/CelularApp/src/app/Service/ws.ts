import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Celular } from '../Entidad/Celular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Ws {
  constructor(private http: HttpClient) { }

  url = "http://localhost:8001/api/Cel"

  listarCelu() {
    return this.http.get<Celular[]>(this.url + "/listar");
  }

  guardarCelu(celular: Celular) {
    return this.http.post<String>(this.url + "/guardar", celular);
  }

  editarCelu(celular: Celular) {
    return this.http.put<String>(this.url + "/editar", celular);
  }

  buscarCelu(celular: Celular) {
    return this.http.post<Celular>(this.url + "/buscar", celular);
}

eliminarC(celular : Celular):Observable<void>{
    return this.http.delete<void>(this.url + "/eliminar", {body : celular});
}

 buscarPorMarca(marca: string): Observable<Celular[]> {
    const params = new HttpParams().set('marca', marca);
    return this.http.get<Celular[]>(this.url + "/buscarPorMarca", { params: params });
  }

}

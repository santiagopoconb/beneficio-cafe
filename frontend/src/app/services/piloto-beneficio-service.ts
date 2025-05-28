import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PilotoBeneficioService {

  private url = 'http://localhost:8081/beneficio/piloto';

  constructor(private http: HttpClient) {}

  listarTodos(page: number = 0, cui?: string, estado?: string): Observable<any> {
  let params = `?page=${page}`;
  if (cui) params += `&cui=${encodeURIComponent(cui)}`;
  if (estado) params += `&estado=${encodeURIComponent(estado)}`;
  return this.http.get<any>(`${this.url}${params}`);
}

  actualizarEstado(payload: any) {
  return this.http.put(`${this.url}`, payload);
}
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransportistaBeneficioService {

  private url = 'http://localhost:8081/beneficio/transportista';

  constructor(private http: HttpClient) {}

  listarTodos(page: number = 0): Observable<any> {
    return this.http.get<any>(`${this.url}?page=${page}`);
  }

  actualizarEstado(payload: any) {
  return this.http.put(`${this.url}`, payload);
}
}
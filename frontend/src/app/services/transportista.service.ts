import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransportistaService {
  private apiUrl = 'http://localhost:8081/transportista';

  constructor(private http: HttpClient) {}

  obtenerTransportistas(page: number = 0): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}`);
  }

  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/all`);
  }
}

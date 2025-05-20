import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PilotoService {
  private apiUrl = 'http://localhost:8081/piloto';

  constructor(private http: HttpClient) {}

  listarPilotos(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  obtenerTiposLiciencia(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/tipoLicencia`);
  }
  
   crearPiloto(data: any): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }
}

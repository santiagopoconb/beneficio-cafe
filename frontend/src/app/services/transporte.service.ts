import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransporteService {
  private apiUrl = 'http://localhost:8081/transporte';

  constructor(private http: HttpClient) {}

  listarTransporte(page: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}`);
  }

   obtenerTiposPlaca(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/tipoPlaca`);
  }
  
  guardarTransporte(data: any): Observable<any> {
  return this.http.post<any>('http://localhost:8081/transporte', data);
}

}
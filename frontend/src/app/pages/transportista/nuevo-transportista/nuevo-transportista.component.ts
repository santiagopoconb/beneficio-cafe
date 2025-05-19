import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

interface RespuestaExito {
  mensaje: string;
}


@Component({
  selector: 'app-nuevo-transportista',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nuevo-transportista.component.html',
  styleUrls: ['./nuevo-transportista.component.css']
})
export class NuevoTransportistaComponent implements OnInit {
  transportista = {
    nitTransportista: '',
    nombreTransportista: '',
    usuarioCreacion: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    const usuario = localStorage.getItem('usuario');
    this.transportista.usuarioCreacion = usuario || 'desconocido';
  }

mensajeExito = '';
mostrarModal = false;

  guardar(): void {
    this.http.post<RespuestaExito>('http://localhost:8081/transportista', this.transportista)
  .subscribe({
    next: (respuesta) => {
      this.mensajeExito = respuesta.mensaje;
      this.mostrarModal = true;
    },
    error: (err) => {
      console.error('Error al guardar:', err);
      alert('Error al registrar transportista.');
    }
  });
  }

  cancelar(): void {
    this.router.navigate(['/transportista']);
  }

  navegarANuevo(): void {
  this.router.navigate(['/transportista/nuevo']);
    }

    cerrarModal(): void {
  this.mostrarModal = false;
  this.router.navigate(['/transportista']); // ✅ redirige
    }
}

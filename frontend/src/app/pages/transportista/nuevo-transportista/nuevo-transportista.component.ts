import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nuevo-transportista',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './nuevo-transportista.component.html',
  styleUrls: ['./nuevo-transportista.component.css']
})
export class NuevoTransportistaComponent implements OnInit {
  transporteForm!: FormGroup;
  mensajeExito = '';
  mostrarModal = false;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    const usuario = sessionStorage.getItem('usuario') || 'desconocido';
    const nitAgricultor = sessionStorage.getItem('nitAgricultor');

    if (!nitAgricultor) {
      alert('Error: No se encontró nitAgricultor. Inicia sesión nuevamente.');
      return;
    }

    this.transporteForm = this.fb.group({
      nitTransportista: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]{1,8}$')]],
      nombreTransportista: ['', Validators.required],
      usuarioCreacion: [usuario],
      nitAgricultor: [nitAgricultor]
    });
  }

  soloLetrasNumeros(event: KeyboardEvent) {
    const char = event.key;
    const regex = /^[a-zA-Z0-9]$/;
    if (!regex.test(char)) {
      event.preventDefault();
    }
  }

  guardar(): void {
    if (this.transporteForm.valid) {
      this.http.post('http://localhost:8081/transportista', this.transporteForm.value)
        .subscribe({
          next: (respuesta: any) => {
            this.mensajeExito = respuesta.mensaje;
            this.mostrarModal = true;
          },
          error: (err) => {
            console.error('Error al guardar:', err);
            if (err.error && err.error.mensaje) {
              alert(err.error.mensaje);
            } else {
              alert('Error inesperado al registrar transportista.');
            }
          }
        });
    } else {
      alert('Por favor complete todos los campos correctamente.');
    }
  }

  cancelar(): void {
    this.router.navigate(['/transportista']);
  }

  navegarANuevo(): void {
    this.router.navigate(['/transportista/nuevo']);
  }

  cerrarModal(): void {
    this.mostrarModal = false;
    this.router.navigate(['/transportista']);
  }
}
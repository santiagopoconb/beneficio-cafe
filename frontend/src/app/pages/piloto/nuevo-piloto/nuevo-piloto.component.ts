import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PilotoService } from '../../../services/piloto.service';
import { TransportistaService } from '../../../services/transportista.service';

@Component({
  selector: 'app-nuevo-piloto',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './nuevo-piloto.component.html',
  styleUrls: ['./nuevo-piloto.component.css']
})
export class NuevoPilotoComponent implements OnInit {
  pilotoForm!: FormGroup;
  transportistas: any[] = [];
  tiposLicencia: any[] = [];

  mensajeExito: string = '';
  mensajeError: string = '';
  mostrarModal: boolean = false;
  mostrarModalError: boolean = false;

  constructor(
    private fb: FormBuilder,
    private pilotoService: PilotoService,
    private transportistaService: TransportistaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.pilotoForm = this.fb.group({
      nitTransportista: ['', Validators.required],
      cui: ['', [Validators.required, Validators.pattern('^[0-9]{13}$')]],
      nombre: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      tipoLicencia: ['', Validators.required],
      fechaVencimiento: ['', Validators.required],
      observaciones: ['']
    });

    this.obtenerTransportistas();
    this.obtenerTiposLicencia();
  }

  obtenerTransportistas(): void {
  this.transportistaService.listarTodos().subscribe(res => this.transportistas = res);
}

  obtenerTiposLicencia(): void {
    this.pilotoService.obtenerTiposLiciencia().subscribe({
      next: (data) => this.tiposLicencia = data,
      error: () => console.error('Error al cargar tipos de licencia')
    });
  }

  validarEdad(): void {
    const fechaNacimiento = new Date(this.pilotoForm.get('fechaNacimiento')?.value);
    const hoy = new Date();
    const edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    const mes = hoy.getMonth() - fechaNacimiento.getMonth();

    if (edad < 18 || (edad === 18 && mes < 0)) {
      this.pilotoForm.get('fechaNacimiento')?.setErrors({ menorEdad: true });
    }
  }

  validarVencimiento(): void {
    const fechaVenc = new Date(this.pilotoForm.get('fechaVencimiento')?.value);
    const hoy = new Date();

    if (fechaVenc <= hoy) {
      this.pilotoForm.get('fechaVencimiento')?.setErrors({ licenciaVencida: true });
    }
  }

  guardar(): void {
    const usuario = sessionStorage.getItem('usuario');
    const formValue = this.pilotoForm.value;

    const payload = {
      ...formValue,
      usuarioCreacion: usuario || 'desconocido'
    };

    this.pilotoService.crearPiloto(payload).subscribe({
      next: (respuesta) => {
        this.mensajeExito = respuesta.mensaje;
        this.mostrarModal = true;
      },
      error: (err) => {
        console.error('Error al guardar transporte:', err);
      console.log('Respuesta error completa:', JSON.stringify(err));

      // 🔸 Capturar mensaje de error del backend (json.message)
      let mensaje = '';

      if (err.status === 400) {
        // Si error es un objeto con "message"
        if (err.error?.message) {
          mensaje = err.error.message;
        } else {
          mensaje = 'Error al registrar piloto (datos inválidos).';
        }
      } else {
        mensaje = 'Error inesperado. Intente nuevamente.';
      }

      alert(mensaje);
      }
    });
  }

  soloNumeros(event: KeyboardEvent) {
  const char = event.key;
  if (!/^[0-9]$/.test(char)) {
    event.preventDefault();
  }
}

  cerrarModal(): void {
    this.mostrarModal = false;
    this.router.navigate(['/piloto']);
  }

  cerrarModalError(): void {
    this.mostrarModalError = false;
  }

  cancelar(): void {
    this.router.navigate(['/piloto']);
  }
}
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { TransporteService } from '../../../services/transporte.service';
import { TransportistaService } from '../../../services/transportista.service';


@Component({
  selector: 'app-nuevo-transporte',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './nuevo-transporte.component.html',
  styleUrls: ['./nuevo-transporte.component.css']
})
export class NuevoTransporteComponent implements OnInit {
  transporteForm!: FormGroup;
  transportistas: any[] = [];
  tiposPlaca: any[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router,
    private transportistaService: TransportistaService,
    private transporteService: TransporteService
  ) {}

  ngOnInit(): void {
    this.transporteForm = this.fb.group({
      nitTransportista: ['', Validators.required],
      tipoPlaca: ['', Validators.required],
      placa: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]{1,6}$')]],
      marca: ['', Validators.required],
      color: ['', Validators.required],
      linea: ['', Validators.required],
      modelo: [null, Validators.required],
      observaciones: ['']
    });

   this.cargarTransportistas();
   this.cargarTiposPlaca();
  }

  cargarTransportistas(): void {
  this.transportistaService.listarTodos().subscribe(res => this.transportistas = res);
}

  cargarTiposPlaca(): void {
  this.transporteService.obtenerTiposPlaca().subscribe(res => this.tiposPlaca = res);
}

mensajeExito = '';
mostrarModal = false;

mensajeError = '';
mostrarModalError = false;

  guardar(): void {
  const usuario = sessionStorage.getItem('usuario');
  this.transporteForm.updateValueAndValidity();
  const formValue = this.transporteForm.value;

  const payload = {
    ...formValue,
    placa: formValue.placa.toUpperCase(),  // Convertimos a mayúsculas
    usuarioCreacion: usuario || 'desconocido'
  };

  this.transporteService.guardarTransporte(payload).subscribe({
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
          mensaje = 'Error al registrar transporte (datos inválidos).';
        }
      } else {
        mensaje = 'Error inesperado. Intente nuevamente.';
      }

      alert(mensaje);
    }
  });
}

soloLetrasNumeros(event: KeyboardEvent) {
  const char = event.key;
  const regex = /^[a-zA-Z0-9]$/;
  if (!regex.test(char)) {
    event.preventDefault();
  }
}

  cancelar(): void {
    this.router.navigate(['/transporte']);
  }

    cerrarModal(): void {
    this.mostrarModal = false;
    this.router.navigate(['/transporte']);
  }

  cerrarModalError(): void {
    this.mostrarModalError = false;
  }

}

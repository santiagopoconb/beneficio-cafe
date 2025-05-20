import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PesajeService } from '../../../services/pesaje.service';

@Component({
  selector: 'app-crear-pesaje',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './crear-pesaje.component.html',
  styleUrls: ['./crear-pesaje.component.css']
})
export class CrearPesajeComponent implements OnInit {
  formularioPesaje!: FormGroup;
  medidas: any[] = [];

  mensajeExito: string = '';
  mensajeError: string = '';
  mostrarModal: boolean = false;
  mostrarModalError: boolean = false;

  constructor(
    private fb: FormBuilder,
    private pesajeService: PesajeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formularioPesaje = this.fb.group({
      idMedidaPeso: [null, Validators.required],
      pesoTotal: [null, Validators.required],
      estado: ['Sin cuenta creada', Validators.required],

    });

    this.obtenerMedidas();
  }
  
  obtenerMedidas(): void {
    this.pesajeService.obtenerMedidas().subscribe({
      next: (data) => {this.medidas = data;
        console.log('Medidas cargadas:', this.medidas);},
      error: () => console.error('Error al cargar tipos de medida')
    });
  }



  guardar(): void {
    const usuario = sessionStorage.getItem('usuario');
    const formValue = this.formularioPesaje.value;
    
    const payload = {
      ...formValue,
      usuarioCreacion: usuario || 'desconocido'
    };

    this.pesajeService.crearPesaje(payload).subscribe({
      next: (respuesta) => {
        this.mensajeExito = respuesta.mensaje;
        this.mostrarModal = true;
      },
      error: (err) => {
        console.error('Error al guardar piloto:', err);
        this.mensajeError = err.error?.message || err.error || 'Error desconocido al crear pesaje.';
        this.mostrarModalError = true;
      }
    });
  }

  cerrarModal(): void {
    this.mostrarModal = false;
    this.router.navigate(['/pesaje']);
  }

  cerrarModalError(): void {
    this.mostrarModalError = false;
  }

  cancelar(): void {
    this.router.navigate(['/pesaje']);
  }
}
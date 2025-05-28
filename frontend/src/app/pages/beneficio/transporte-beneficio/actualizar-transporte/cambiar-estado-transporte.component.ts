import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TransporteBeneficioService } from '../../../../services/transporte-beneficio.service';

@Component({
  standalone: true,
  selector: 'app-cambiar-estado-transporte',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cambiar-estado-transporte.component.html',
  styleUrls: ['./cambiar-estado-transporte.component.css']
})
export class CambiarEstadoTransporteComponent implements OnInit {
  form!: FormGroup;
  estados: any[] = [];
  placa!: string;
  estadoActual!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private transporteService: TransporteBeneficioService
  ) {}

  estadoActualTexto!: string;

ngOnInit(): void {
  this.placa = this.route.snapshot.queryParamMap.get('placa') || '';
  this.estadoActualTexto = this.route.snapshot.queryParamMap.get('estadoActual') || '';

  this.form = this.fb.group({
    placa: [{ value: this.placa, disabled: true }],
    estadoTransportista: ['', Validators.required],
    observaciones: [''] 
  });

  this.http.get<any[]>('http://localhost:8081/beneficio/transportista/estado').subscribe(data => {
    this.estados = data;
  });
}

guardar(): void {
  // Buscar el texto del estado nuevo a partir del id seleccionado
  const nuevoEstadoTexto = this.estados.find(e => e.idEstadoTransportistaBeneficio == this.form.get('estadoTransportista')?.value)?.estadoTransportistaBeneficio;

  if (!nuevoEstadoTexto) {
    alert('Debe seleccionar un estado');
    return;
  }

  if (nuevoEstadoTexto === this.estadoActualTexto) {
    alert('El transporte ya se encuentra '+ nuevoEstadoTexto);
    return;
  }

  const payload = {
    placa: this.placa,
    idEstado: this.form.get('estadoTransportista')?.value,
    observaciones: this.form.get('observaciones')?.value,
    usuarioCreacion: sessionStorage.getItem('usuario') || 'desconocido'
  };

  this.transporteService.actualizarEstado(payload).subscribe((response: any) => {
    alert(response.mensaje);
    this.router.navigate(['/beneficio/transporte-beneficio']);
  });
}

  cancelar(): void {
    this.router.navigate(['/beneficio/transporte-beneficio']);
  }
}

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { PilotoBeneficioService } from '../../../../services/piloto-beneficio-service';

@Component({
  standalone: true,
  selector: 'app-cambiar-estado-piloto',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cambiar-estado-piloto.component.html',
  styleUrls: ['./cambiar-estado-piloto.component.css']
})
export class CambiarEstadoPilotoComponent implements OnInit {
  form!: FormGroup;
  estados: any[] = [];
  cui!: string;
  estadoActual!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private pilotoService: PilotoBeneficioService
  ) {}

  estadoActualTexto!: string;

ngOnInit(): void {
  this.cui = this.route.snapshot.queryParamMap.get('cui') || '';
  this.estadoActualTexto = this.route.snapshot.queryParamMap.get('estadoActual') || '';

  this.form = this.fb.group({
    cui: [{ value: this.cui, disabled: true }],
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
    alert('El piloto ya se encuentra '+ nuevoEstadoTexto);
    return;
  }

  const payload = {
    cui: this.cui,
    idEstado: this.form.get('estadoTransportista')?.value,
    observaciones: this.form.get('observaciones')?.value,
    usuarioCreacion: sessionStorage.getItem('usuario') || 'desconocido'
  };

  this.pilotoService.actualizarEstado(payload).subscribe((response: any) => {
    alert(response.mensaje);
    this.router.navigate(['/beneficio/piloto-beneficio']);
  });
}

  cancelar(): void {
    this.router.navigate(['/beneficio/piloto-beneficio']);
  }
}

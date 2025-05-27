import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TransportistaBeneficioService } from '../../../../services/transportista-beneficio.service';

@Component({
  standalone: true,
  selector: 'app-cambiar-estado-transportista',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cambiar-estado-transportista.component.html',
  styleUrls: ['./cambiar-estado-transportista.component.css']
})
export class CambiarEstadoTransportistaComponent implements OnInit {
  form!: FormGroup;
  estados: any[] = [];
  nitTransportista!: string;
  estadoActual!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private transportistaService: TransportistaBeneficioService
  ) {}

  estadoActualTexto!: string;

ngOnInit(): void {
  this.nitTransportista = this.route.snapshot.queryParamMap.get('nitTransportista') || '';
  this.estadoActualTexto = this.route.snapshot.queryParamMap.get('estadoActual') || '';

  this.form = this.fb.group({
    nitTransportista: [{ value: this.nitTransportista, disabled: true }],
    estadoTransportista: ['', Validators.required]
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
    nitTransportista: this.nitTransportista,
    estadoTransportista: this.form.get('estadoTransportista')?.value,
    usuarioCreacion: sessionStorage.getItem('usuario') || 'desconocido'
  };

  this.transportistaService.actualizarEstado(payload).subscribe((response: any) => {
    alert(response.mensaje);
    this.router.navigate(['/beneficio/transportista-beneficio']);
  });
}

  cancelar(): void {
    this.router.navigate(['/beneficio/transportista-beneficio']);
  }
}

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TransporteBeneficioService } from '../../../services/transporte-beneficio.service';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-transporte-beneficiario',
  imports: [CommonModule, FormsModule],
  templateUrl: './././transporte-beneficio.component.html',
  styleUrls: ['./././transporte-beneficio.component.css']
})

export class TransporteBeneficiarioComponent implements OnInit {
  transporte: any[] = [];
  transporteFiltrado: any[] = [];
  paginaActual = 0;
  totalPaginas = 0;

  busquedaPlaca = '';
  filtroEstado = '';
  estados: any[] = []; 

  constructor(
    private transporteBeneficioService: TransporteBeneficioService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.cargarTransporte();
    this.cargarEstados();
  }

  cargarTransporte(): void {
    this.transporteBeneficioService.listarTodos(this.paginaActual)
      .subscribe(data => {
        this.transporte = data.content;
        this.transporteFiltrado = [...this.transporte];
        this.totalPaginas = data.totalPages;
      });
  }

  cargarEstados(): void {
  this.http.get<any[]>('http://localhost:8081/beneficio/transportista/estado')
    .subscribe(data => {
      this.estados = data;
      console.log('Estados cargados desde backend:', data);
    });
}

  aplicarFiltros(): void {
  console.log('Filtro estado seleccionado (id):', this.filtroEstado);
  this.transporteBeneficioService.listarTodos(this.paginaActual, this.busquedaPlaca, this.filtroEstado)
    .subscribe(data => {
      this.transporte = data.content;
      this.transporteFiltrado = [...this.transporte];
      this.totalPaginas = data.totalPages;
    });
}

  limpiarFiltros(): void {
    this.busquedaPlaca = '';
    this.filtroEstado = '';
    this.cargarTransporte();
  }

  paginaAnterior(): void {
    if (this.paginaActual > 0) {
      this.paginaActual--;
      this.cargarTransporte();
    }
  }

  paginaSiguiente(): void {
    if (this.paginaActual + 1 < this.totalPaginas) {
      this.paginaActual++;
      this.cargarTransporte();
    }
  }

  cambiarEstado(transporte: any): void {
  this.router.navigate(['/beneficio/cambiar-estado-transporte'], {
      queryParams: {
        placa: transporte.placa,
        estadoActual: transporte.estado // Ahora enviamos el texto (por ejemplo "Activo")
      }
    });
  }

  regresarInicio(): void {
    this.router.navigate(['/beneficio/inicio']);
  }
}

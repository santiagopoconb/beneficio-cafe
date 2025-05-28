import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { PilotoBeneficioService } from '../../../services/piloto-beneficio-service';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-piloto-beneficiario',
  imports: [CommonModule, FormsModule],
  templateUrl: './././piloto-beneficio.component.html',
  styleUrls: ['./././piloto-beneficio.component.css']
})

export class PiltoBeneficiarioComponent implements OnInit {
  piloto: any[] = [];
  pilotoFiltrado: any[] = [];
  paginaActual = 0;
  totalPaginas = 0;

  busquedaCui = '';
  filtroEstado = '';
  estados: any[] = []; 

  constructor(
    private pilotoBeneficioService: PilotoBeneficioService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.cargarPiloto();
    this.cargarEstados();
  }

  cargarPiloto(): void {
    this.pilotoBeneficioService.listarTodos(this.paginaActual)
      .subscribe(data => {
        this.piloto = data.content;
        this.pilotoFiltrado = [...this.piloto];
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
  console.log('Valor de busqueda cui', this.busquedaCui)
  this.pilotoBeneficioService.listarTodos(this.paginaActual, this.busquedaCui, this.filtroEstado)
    .subscribe(data => {
      this.piloto = data.content;
      this.pilotoFiltrado = [...this.piloto];
      this.totalPaginas = data.totalPages;
    });
}

  limpiarFiltros(): void {
    this.busquedaCui = '';
    this.filtroEstado = '';
    this.cargarPiloto();
  }

  paginaAnterior(): void {
    if (this.paginaActual > 0) {
      this.paginaActual--;
      this.cargarPiloto();
    }
  }

  paginaSiguiente(): void {
    if (this.paginaActual + 1 < this.totalPaginas) {
      this.paginaActual++;
      this.cargarPiloto();
    }
  }

  cambiarEstado(piloto: any): void {
  this.router.navigate(['/beneficio/cambiar-estado-piloto'], {
      queryParams: {
        cui: piloto.cui,
        estadoActual: piloto.estado // Ahora enviamos el texto (por ejemplo "Activo")
      }
    });
  }

  regresarInicio(): void {
    this.router.navigate(['/beneficio/inicio']);
  }
}

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TransporteBeneficioService } from '../../../services/transporte-beneficio.service';

@Component({
  standalone: true,
  selector: 'app-transporte-beneficiario',
  imports: [CommonModule],
  templateUrl: './././transporte-beneficio.component.html',
  styleUrls: ['./././transporte-beneficio.component.css']
})
export class TransporteBeneficiarioComponent implements OnInit {
  transporte: any[] = [];
  paginaActual = 0;
  totalPaginas = 0;

  constructor(
    private transporteBeneficioService: TransporteBeneficioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarTransporte();
  }

  cargarTransporte(): void {
    this.transporteBeneficioService.listarTodos(this.paginaActual)
      .subscribe(data => {
        this.transporte = data.content;
        this.totalPaginas = data.totalPages;
      });
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

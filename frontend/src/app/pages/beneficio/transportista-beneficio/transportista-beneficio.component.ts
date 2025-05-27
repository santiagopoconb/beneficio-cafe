import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TransportistaBeneficioService } from '../../../services/transportista-beneficio.service';

@Component({
  standalone: true,
  selector: 'app-transportistas-beneficiario',
  imports: [CommonModule],
  templateUrl: './././transportista-beneficio.component.html',
  styleUrls: ['./././transportista-beneficio.component.css']
})
export class TransportistasBeneficiarioComponent implements OnInit {
  transportistas: any[] = [];
  paginaActual = 0;
  totalPaginas = 0;

  constructor(
    private transportistaBeneficioService: TransportistaBeneficioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarTransportistas();
  }

  cargarTransportistas(): void {
    this.transportistaBeneficioService.listarTodos(this.paginaActual)
      .subscribe(data => {
        this.transportistas = data.content;
        this.totalPaginas = data.totalPages;
      });
  }

  paginaAnterior(): void {
    if (this.paginaActual > 0) {
      this.paginaActual--;
      this.cargarTransportistas();
    }
  }

  paginaSiguiente(): void {
    if (this.paginaActual + 1 < this.totalPaginas) {
      this.paginaActual++;
      this.cargarTransportistas();
    }
  }

  cambiarEstado(transportista: any): void {
  this.router.navigate(['/beneficio/cambiar-estado-transportista'], {
      queryParams: {
        nitTransportista: transportista.nitTransportista,
        estadoActual: transportista.estadoTransportista // Ahora enviamos el texto (por ejemplo "Activo")
      }
    });
  }

  regresarInicio(): void {
    this.router.navigate(['/beneficio/inicio']);
  }
}

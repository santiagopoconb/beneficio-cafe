import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // ✅ Importar esto
import { TransportistaService } from '../../services/transportista.service';

@Component({
  selector: 'app-transportista',
  standalone: true, // Standalone component
  imports: [CommonModule], // ✅ Importar directivas básicas como *ngFor, *ngIf
  templateUrl: './transportista.component.html',
  styleUrls: ['./transportista.component.css']
})
export class TransportistaComponent implements OnInit {
  transportistas: any[] = [];
  paginaActual = 0;
  totalPaginas = 0;

  constructor(
    private transportistaService: TransportistaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarTransportistas();
  }

  cargarTransportistas(): void {
    this.transportistaService.obtenerTransportistas(this.paginaActual).subscribe(data => {
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

  siguientePagina(): void {
    if (this.paginaActual < this.totalPaginas - 1) {
      this.paginaActual++;
      this.cargarTransportistas();
    }
  }

  navegarANuevo(): void {
    this.router.navigate(['/transportista/nuevo']);
  }

  irAInicio(): void {
  this.router.navigate(['/inicio']);
}
}
import { Component, OnInit } from '@angular/core';
import { TransporteService } from '../../services/transporte.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-transporte',
  templateUrl: './transporte.component.html',
  styleUrls: ['./transporte.component.css'],
  imports: [CommonModule]
})
export class TransporteComponent implements OnInit {
  transporteList: any[] = [];
  page = 0;
  totalPages = 0;

  constructor(private transporteService: TransporteService, private router: Router) {}

  ngOnInit(): void {
    this.cargarTransporte();
  }

  cargarTransporte(): void {
    this.transporteService.listarTransporte(this.page).subscribe(res => {
      this.transporteList = res.content;
      this.totalPages = res.totalPages;
    });
  }

  siguientePagina(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.cargarTransporte();
    }
  }

  paginaAnterior(): void {
    if (this.page > 0) {
      this.page--;
      this.cargarTransporte();
    }
  }

  irANuevo(): void {
    this.router.navigate(['/nuevo-transporte']);
  }

  regresar(): void {
    this.router.navigate(['/inicio']);
  }
}
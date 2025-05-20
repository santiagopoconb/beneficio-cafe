import { Component, OnInit } from '@angular/core';
import { PesajeService } from '../../services/pesaje.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pesaje',
  standalone: true,
  imports: [CommonModule], // NECESARIO para usar pipes como date, ngIf, ngFor
  templateUrl: './pesaje.component.html',
  styleUrls: ['./pesaje.component.css']
})
export class PesajeComponent implements OnInit {
  /*pilotoList: any[] = [];
  page = 0;
  size = 10;
  totalPages = 0;*/

  constructor(private pesajeService: PesajeService, private router: Router) {}

  ngOnInit(): void {
    //this.cargarPilotos();
  }/*

  cargarPilotos(): void {
    this.pilotoService.listarPilotos(this.page, this.size).subscribe({
      next: (res) => {
        this.pilotoList = res.content;
        this.totalPages = res.totalPages;
      }
    });
  }

  paginaAnterior(): void {
    if (this.page > 0) {
      this.page--;
      this.cargarPilotos();
    }
  }

  siguientePagina(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.cargarPilotos();
    }
  }*/

  irANuevo(): void {
    this.router.navigate(['/crear-pesaje']);
  }

  regresar(): void {
    window.location.href = '/inicio';
  }
}
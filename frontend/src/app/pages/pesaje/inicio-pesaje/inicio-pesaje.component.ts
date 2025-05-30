import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-inicio-pesaje',
  imports: [CommonModule],
  templateUrl: './inicio-pesaje.component.html',
  styleUrl: './inicio-pesaje.component.css'
})
export class InicioPesajeComponent {
  constructor(private router: Router) {}

  navegar(ruta: string) {
    console.log('Navegar a:', ruta);
    this.router.navigate([ruta]);
  }

  fechaLogin: string = new Date().toLocaleString('es-GT');

  salir() {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}
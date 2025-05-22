import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-inicio-beneficio',
  imports: [CommonModule],
  templateUrl: './inicio-beneficio.component.html',
  styleUrl: './inicio-beneficio.component.css'
})
export class InicioBeneficioComponent {
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
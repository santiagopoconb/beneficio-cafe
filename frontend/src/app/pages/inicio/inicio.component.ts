import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router'; 


@Component({
  selector: 'app-inicio',
  standalone: true,              // <- AGREGAR ESTO TAMBIÉN
  imports: [RouterModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'] // <- CORRECTO
})
export class InicioComponent {
  nombreUsuario = sessionStorage.getItem('usuario') || 'Invitado';

  constructor(private router: Router) {}

  cerrarSesion() {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}

import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
  this.authService.login(this.username, this.password).subscribe({
    next: (response) => {
      const token = response.jwtToken;
      const usuario = response.usuario;
      const rol = response.rol;
      const nitAgricultor = response.nitAgricultor;

      // ✅ Guardar todo ANTES de navegar
      this.authService.guardarToken(token);
      sessionStorage.setItem('usuario', usuario);
      sessionStorage.setItem('rol', rol.toString());
      sessionStorage.setItem('nitAgricultor', nitAgricultor);

      console.log('Token recibido:', token);
      console.log('usuario recibido', usuario);
      console.log('Rol recibido: ', rol);
      console.log('NitAg recibido: ', nitAgricultor);

      // ✅ Redirigir según el rol
      setTimeout(() => {
        if (rol === 'ROLE_BENEFICIO') {
          this.router.navigate(['/beneficio/inicio']);
        } else if (rol === 'ROLE_PESOCABAL') {
            this.router.navigate(['/pesaje/inicio']);
          } else {
          this.router.navigate(['/inicio']);
        }
      }, 0);
    },
    error: () => {
      this.errorMessage = 'Error al autenticar';
    }
  });
}

}

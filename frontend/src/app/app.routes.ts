import { Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'inicio',
    loadComponent: () => import('./pages/inicio/inicio.component').then(m => m.InicioComponent),
    canActivate: [AuthGuard]
  },
  {
  path: 'transportista',
  loadComponent: () =>
    import('./pages/transportista/transportista.component').then(m => m.TransportistaComponent),
    canActivate: [AuthGuard]
  },
  {
  path: 'transportista/nuevo',
  loadComponent: () => import('./pages/transportista/nuevo-transportista/nuevo-transportista.component')
    .then(m => m.NuevoTransportistaComponent),
    canActivate: [AuthGuard]
  },
  {
  path: 'transporte',
  loadComponent: () =>
    import('./pages/transporte/transporte.component').then(m => m.TransporteComponent),
   canActivate: [AuthGuard]
},
  {
    path: 'nuevo-transporte',
    loadComponent: () =>
      import('./pages/transporte/nuevo-transporte/nuevo-transporte.component').then(m => m.NuevoTransporteComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'piloto',
    loadComponent: () => import('./pages/piloto/piloto.component').then(m => m.PilotoComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'nuevo-piloto',
    loadComponent: () => import('./pages/piloto/nuevo-piloto/nuevo-piloto.component').then(m => m.NuevoPilotoComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'pesaje',
    loadComponent: () => import('./pages/pesaje/pesaje.component').then(m => m.PesajeComponent),
    canActivate: [AuthGuard]
  },
    {
    path: 'crear-pesaje',
    loadComponent: () => import('./pages/pesaje/crear-pesaje/crear-pesaje.component').then(m => m.CrearPesajeComponent),
    canActivate: [AuthGuard]
  },


  { path: '**', redirectTo: 'login' } // ← ESTE VA SIEMPRE AL FINAL
];

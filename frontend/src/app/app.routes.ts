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
      path: 'beneficio/inicio',
      canActivate: [AuthGuard],
      loadComponent: () =>
        import('./pages/beneficio/inicio-beneficio/inicio-beneficio.component').then(m => m.InicioBeneficioComponent)
    },
    {
    path: 'pesaje/inicio',
    canActivate: [AuthGuard],
    loadComponent: () =>
      import('./pages/pesaje/inicio-pesaje/inicio-pesaje.component').then(m => m.InicioPesajeComponent)
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






//Beneficio
  {
    path: 'beneficio/transportista-beneficio',
    loadComponent: () =>
      import('./pages/beneficio/transportista-beneficio/transportista-beneficio.component')
        .then(m => m.TransportistasBeneficiarioComponent),
      canActivate: [AuthGuard]
  },
  {
    path: 'beneficio/cambiar-estado-transportista',
    loadComponent: () =>
      import('./pages/beneficio/transportista-beneficio/actualizar-transportista/cambiar-estado-transportista.component')
        .then(m => m.CambiarEstadoTransportistaComponent),
    canActivate: [AuthGuard]
  },
   {
    path: 'beneficio/transporte-beneficio',
    loadComponent: () =>
      import('./pages/beneficio/transporte-beneficio/transporte-beneficio.component')
        .then(m => m.TransporteBeneficiarioComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'beneficio/cambiar-estado-transporte',
    loadComponent: () =>
      import('./pages/beneficio/transporte-beneficio/actualizar-transporte/cambiar-estado-transporte.component')
        .then(m => m.CambiarEstadoTransporteComponent),
    canActivate: [AuthGuard]
  },

  {
    path: 'beneficio/piloto-beneficio',
    loadComponent: () =>
      import('./pages/beneficio/piloto-beneficio/piloto-beneficio.component')
        .then(m => m.PiltoBeneficiarioComponent),
    canActivate: [AuthGuard]
  },
  {
    path: 'beneficio/cambiar-estado-piloto',
    loadComponent: () =>
      import('./pages/beneficio/piloto-beneficio/actualizar-transporte/cambiar-estado-piloto.component')
        .then(m => m.CambiarEstadoPilotoComponent),
    canActivate: [AuthGuard]
  },


  { path: '**', redirectTo: 'login' } // ← ESTE VA SIEMPRE AL FINAL
];

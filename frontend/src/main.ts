import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';
import { authInterceptor } from './app/interceptors/auth.interceptor';
import { AuthGuard } from './app/auth.guard';

bootstrapApplication(AppComponent, {
  ...appConfig,
  providers: [
    provideHttpClient(withInterceptors([authInterceptor])),
    AuthGuard,
    ...(appConfig.providers || [])
  ]
}).catch((err) => console.error(err));
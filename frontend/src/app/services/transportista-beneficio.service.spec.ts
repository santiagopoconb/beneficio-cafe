import { TestBed } from '@angular/core/testing';

import { TransportistaBeneficioService } from './transportista-beneficio.service';

describe('TransportistaBeneficioService', () => {
  let service: TransportistaBeneficioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransportistaBeneficioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

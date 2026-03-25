import { TestBed } from '@angular/core/testing';

import { WS } from './ws';

describe('WS', () => {
  let service: WS;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WS);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

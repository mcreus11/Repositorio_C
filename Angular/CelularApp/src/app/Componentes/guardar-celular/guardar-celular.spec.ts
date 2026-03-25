import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardarCelular } from './guardar-celular';

describe('GuardarCelular', () => {
  let component: GuardarCelular;
  let fixture: ComponentFixture<GuardarCelular>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuardarCelular]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuardarCelular);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

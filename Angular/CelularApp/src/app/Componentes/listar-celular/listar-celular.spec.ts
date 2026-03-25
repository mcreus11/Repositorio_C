import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCelular } from './listar-celular';

describe('ListarCelular', () => {
  let component: ListarCelular;
  let fixture: ComponentFixture<ListarCelular>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarCelular]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarCelular);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

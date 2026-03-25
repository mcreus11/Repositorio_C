import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarCelular } from './editar-celular';

describe('EditarCelular', () => {
  let component: EditarCelular;
  let fixture: ComponentFixture<EditarCelular>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarCelular]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarCelular);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

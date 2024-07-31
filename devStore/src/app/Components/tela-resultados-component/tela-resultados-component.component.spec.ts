import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TelaResultadosComponentComponent } from './tela-resultados-component.component';

describe('TelaResultadosComponentComponent', () => {
  let component: TelaResultadosComponentComponent;
  let fixture: ComponentFixture<TelaResultadosComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TelaResultadosComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TelaResultadosComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

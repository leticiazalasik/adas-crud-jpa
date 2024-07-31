import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuProdutoComponentComponent } from './menu-produto-component.component';

describe('MenuProdutoComponentComponent', () => {
  let component: MenuProdutoComponentComponent;
  let fixture: ComponentFixture<MenuProdutoComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuProdutoComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuProdutoComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

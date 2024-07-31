import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuGeralComponentComponent } from './menu-geral-component.component';

describe('MenuGeralComponentComponent', () => {
  let component: MenuGeralComponentComponent;
  let fixture: ComponentFixture<MenuGeralComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuGeralComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuGeralComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

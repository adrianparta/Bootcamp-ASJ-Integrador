import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarProveedoresComponent } from './listar-proveedores.component';

describe('ListarProveedoresComponent', () => {
  let component: ListarProveedoresComponent;
  let fixture: ComponentFixture<ListarProveedoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListarProveedoresComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListarProveedoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

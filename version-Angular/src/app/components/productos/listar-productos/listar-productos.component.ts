import { Component } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';

@Component({
  selector: 'app-listar-productos',
  templateUrl: './listar-productos.component.html',
  styleUrl: './listar-productos.component.css'
})
export class ListarProductosComponent {

  datos!: any[];

  constructor(public serv: ServiceProductoService){
  }  

  ngOnInit() {
    this.serv.getProductos().subscribe((data: any[]) => {
      this.datos = data;
    });
  }
}

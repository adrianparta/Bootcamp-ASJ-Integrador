import { Component } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Product } from '../../../models/products';

@Component({
  selector: 'app-listar-productos',
  templateUrl: './listar-productos.component.html',
  styleUrl: './listar-productos.component.css'
})
export class ListarProductosComponent {

  datos!: Product[];

  constructor(public serv: ServiceProductoService){
  }

  ngOnInit() {
    this.getProducts();
  }

  getProducts(){
    this.serv.getProducts().subscribe((data: Product[]) => {
      this.datos = data;
    });
  }

  deleteProduct(id: number | undefined){
    this.serv.deleteProduct(id).subscribe();
    this.getProducts();
  }
}

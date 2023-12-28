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
      this.ordenar();
    });
  }

  deleteProduct(id: number | undefined){
    if(confirm('¿Está seguro que desea eliminar el producto?')){
      this.serv.deleteProduct(id).subscribe();
      this.getProducts();
      this.ordenar();
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  ordenar(){
    this.datos.sort(function(a, b) {
      return a.name.localeCompare(b.name);
    });
  }

}

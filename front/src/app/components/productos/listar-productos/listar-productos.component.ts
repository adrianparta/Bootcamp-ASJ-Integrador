import { Component } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Producto } from '../../../models/producto';
import { ProveedorService } from '../../../services/service-proveedor.service';

@Component({
  selector: 'app-listar-productos',
  templateUrl: './listar-productos.component.html',
  styleUrl: './listar-productos.component.css'
})
export class ListarProductosComponent {

  datos!: Producto[];
  filterText: string = '';

  constructor(public serv: ServiceProductoService, public servSupplier: ProveedorService){
  }

  ngOnInit() {
    this.getProducts();
    this.filterText = '';
  }

  getProducts(){
    this.serv.obtenerProductos().subscribe((data: Producto[]) => {
      this.datos = data;
      this.ordenar();
    });
  }

  deleteProduct(id: number | undefined){
    if(confirm('¿Está seguro que desea eliminar el producto?')){
      this.serv.borrarProducto(id).subscribe();
      this.getProducts();
      this.ordenar();
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  ordenar(){
    this.datos.sort(function(a, b) {
      return a.nombre.localeCompare(b.nombre);
    });
  }

}

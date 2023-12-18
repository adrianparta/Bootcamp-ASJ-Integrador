import { Injectable } from '@angular/core';
import { productos } from '../data/productos';

@Injectable({
  providedIn: 'root'
})
export class ServiceProductoService {

  agregarProducto(producto: any){
    productos.push(producto);
  }

  public getProductos(){
    return productos;
  }
}

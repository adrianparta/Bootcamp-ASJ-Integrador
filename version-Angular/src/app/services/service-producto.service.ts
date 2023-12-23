import { Injectable } from '@angular/core';
import { productos } from '../data/productos';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceProductoService {

  constructor(private http: HttpClient) { }
  url = 'http://localhost:3000/productos';

  agregarProducto(producto: any): Observable<any>{
    return this.http.post(this.url + '/', producto);
  }

  public getProductos(): Observable<any>{
    return this.http.get(this.url);
  }

  public updateProduct(product : any): Observable<any>{
    return this.http.put(this.url + '/' + product.id, product);
  }
}

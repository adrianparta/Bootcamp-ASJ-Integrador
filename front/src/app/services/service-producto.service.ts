import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ServiceProductoService {

  private urlCategories = 'assets/data/categories.json';

  constructor(private http: HttpClient) {}
  url = 'http://localhost:3000/productos/';

  addProduct(producto: Producto): Observable<Producto>{
    return this.http.post<Producto>(this.url, producto);
  }

  public obtenerProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url);
  }

  public updateProduct(product: Producto): Observable<Producto>{
    return this.http.put<Producto>(this.url + product.id, product);
  }

  public borrarProducto(id: number | undefined): Observable<Producto>{
    return this.http.delete<Producto>(this.url + id);
  }

  public getCategories(): Observable<any>{
    return this.http.get<any>('http://localhost:3000/categorias/');
  }

  public getSingleProduct(id: number): Observable<Producto>{
    return this.http.get<Producto>(this.url + id);
  }

  public addCategory(string: string): Observable<any>{
    return this.http.post<any>('http://localhost:3000/categorias/', {"categoria": string});
  }
}

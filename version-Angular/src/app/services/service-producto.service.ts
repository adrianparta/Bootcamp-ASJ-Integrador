import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/products';

@Injectable({
  providedIn: 'root'
})
export class ServiceProductoService {

  private urlCategories = 'assets/data/categories.json';

  constructor(private http: HttpClient) {}
  url = 'http://localhost:3000/productos/';

  addProduct(producto: Product): Observable<Product>{
    return this.http.post<Product>(this.url, producto);
  }

  public getProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(this.url);
  }

  public updateProduct(product: Product): Observable<Product>{
    return this.http.put<Product>(this.url + product.id, product);
  }

  public deleteProduct(id: number | undefined): Observable<Product>{
    return this.http.delete<Product>(this.url + id);
  }

  public getCategories(): Observable<string[]>{
    return this.http.get<string[]>(this.urlCategories);
  }
}

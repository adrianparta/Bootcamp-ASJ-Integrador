import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  
  constructor(private http: HttpClient) {}
  url = 'http://localhost:8080/productos/';
  
  agregarProducto(producto: Producto): Observable<Producto>{
    return this.http.post<Producto>(this.url, producto);
  }

  public obtenerProductos(): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url);
  }
  
  public obtenerProductosPorEstado(estado: boolean): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + 'estado/' + estado);
  }
  
  public obtenerProductosPorProveedor(id: number, estado: boolean): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + 'proveedor/' + id + '/' + estado);
  }

  public obtenerProductosPorCategoria(id: number, estado: boolean): Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + 'categoria/' + id + '/' + estado);
  }

  public obtenerProducto(id: number): Observable<Producto>{
    return this.http.get<Producto>(this.url + id);
  }
  
  public modificarProducto(producto: Producto): Observable<Producto>{
    return this.http.put<Producto>(this.url + producto.id, producto);
  }
  
  public modificarEstadoProducto(id: number | undefined): Observable<Producto>{
    return this.http.put<Producto>(this.url + id + '/estado', {});
  }

  public agregarCategoria(categoria: string): Observable<Categoria>{
    return this.http.post<Categoria>('http://localhost:8080/categorias/', {"categoria": categoria});
  }

  public obtenerCategorias(): Observable<Categoria[]>{
    return this.http.get<Categoria[]>('http://localhost:8080/categorias/');
  }
  
  public obtenerCategoriasActivas(): Observable<Categoria[]>{
    return this.http.get<Categoria[]>('http://localhost:8080/categorias/activas');
  }

  public modificarCategoria(categoria: Categoria): Observable<Categoria>{
    return this.http.put<Categoria>('http://localhost:8080/categorias/' + categoria.id, categoria);
  }
}

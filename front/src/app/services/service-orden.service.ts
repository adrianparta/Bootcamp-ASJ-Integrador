import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Orden } from '../models/orden';
import { Observable } from 'rxjs';
import { identifierName } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrdenService {

  constructor(private http: HttpClient) {}

  url = 'http://localhost:8080/ordenes/';

  public agregarOrden(order: Orden): Observable<Orden>{
    return this.http.post<Orden>(this.url, order);
  }

  public obtenerOrdenes(): Observable<Orden[]>{
    return this.http.get<Orden[]>(this.url);
  }

  public obtenerOrden(id: number): Observable<Orden>{
    return this.http.get<Orden>(this.url + id);
  }

  public modificarEstadoOrden(id: number | undefined): Observable<Orden>{
    return this.http.put<Orden>(this.url + id, {});
  }
}

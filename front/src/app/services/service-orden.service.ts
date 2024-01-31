import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Orden } from '../models/orden';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrdenService {

  constructor(private http: HttpClient) {}

  url = 'http://localhost:3000/ordenes/';

  public addOrder(order: Orden): Observable<Orden>{
    return this.http.post<Orden>(this.url, order);
  }

  public getOrders(): Observable<Orden[]>{
    return this.http.get<Orden[]>(this.url);
  }

  public getSingleOrder(id: number): Observable<Orden>{
    return this.http.get<Orden>(this.url + id);
  }

  public updateOrder(order: Orden): Observable<Orden>{
    return this.http.put<Orden>(this.url + order.id, order);
  }
}

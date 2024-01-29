import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '../models/orden';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrdenService {

  constructor(private http: HttpClient) {}

  url = 'http://localhost:3000/ordenes/';

  public addOrder(order: Order): Observable<Order>{
    return this.http.post<Order>(this.url, order);
  }

  public getOrders(): Observable<Order[]>{
    return this.http.get<Order[]>(this.url);
  }

  public getSingleOrder(id: number): Observable<Order>{
    return this.http.get<Order>(this.url + id);
  }

  public updateOrder(order: Order): Observable<Order>{
    return this.http.put<Order>(this.url + order.id, order);
  }
}

import { Injectable } from '@angular/core';
import { proveedores } from '../data/proveedores';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';

@Injectable({
  providedIn: 'root'
})
export class ServiceProveedorService{

  url = 'http://localhost:3000/proveedores/';

  constructor(private http: HttpClient) { }

  agregarProveedor(proveedor: any): Observable<any>{
    return this.http.post(this.url, proveedor);
  }

  public getProveedores(): Observable<any>{
    return this.http.get(this.url);
  }

  public getSingleProveedor(id: string): Observable<any>{
    return this.http.get(this.url + id);
  }

  public deleteSupplier(id: number): Observable<any>{
    return this.http.delete(this.url + id);
  }

  public updateSupplier(supplier: any): Observable<any>{
    return this.http.put(this.url + supplier.id, supplier);
  }

  public getLastId(){
    return proveedores[proveedores.length - 1].id;
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../models/supplier';

@Injectable({
  providedIn: 'root'
})
export class ServiceProveedorService{

  url = 'http://localhost:3000/proveedores/';
  private url_countries: string = 'assets/data/countries.json';
  private url_states: string = 'assets/data/states.json'
  private url_cities: string = 'assets/data/cities.json'

  getCountries(): Observable<any>{
    return this.http.get(this.url_countries);
  }
  getStates(): Observable<any>{
    return this.http.get(this.url_states);
  }
  getCities(): Observable<any>{
    return this.http.get(this.url_cities);
  }

  constructor(private http: HttpClient) {}

  public addSupplier(supplier: Supplier): Observable<Supplier>{
    return this.http.post<Supplier>(this.url, supplier);
  }

  public getSuppliers(): Observable<Supplier[]>{
    return this.http.get<Supplier[]>(this.url);
  }

  public getSingleSupplier(id: number): Observable<Supplier>{
    return this.http.get<Supplier>(this.url + id);
  }

  public deleteSupplier(id: number | undefined): Observable<Supplier>{
    return this.http.delete<Supplier>(this.url + id);
  }

  public updateSupplier(supplier: Supplier): Observable<Supplier>{
    return this.http.put<Supplier>(this.url + supplier.id, supplier);
  }
}

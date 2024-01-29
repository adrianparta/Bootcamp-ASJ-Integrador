import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../models/proveedor';
import { ServiceProductoService } from './service-producto.service';
import { Product } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ServiceProveedorService{

  url = 'http://localhost:3000/proveedores/';


  getCountries(): Observable<any>{
    return this.http.get<String[]>('http://localhost:3000/paises/');
  }
  getStates(): Observable<any>{
    return this.http.get<any[]>('http://localhost:3000/provincias/');
    
  }
  getCities(): Observable<any>{
    return this.http.get<String[]>('http://localhost:3000/localidades/');

  }

  constructor(private http: HttpClient, private servProduct: ServiceProductoService) {}

  public addSupplier(supplier: Supplier): Observable<Supplier>{
    return this.http.post<Supplier>(this.url, supplier);
  }

  public getSuppliers(): Observable<Supplier[]>{
    return this.http.get<Supplier[]>(this.url);
  }

  public getSingleSupplier(id: number): Observable<Supplier>{
    return this.http.get<Supplier>(this.url + id);
  }

  public deleteSupplier(id: number | undefined, supplierName: string): Observable<Supplier>{
    this.servProduct.getProducts().subscribe((data: Product[])=>{
      data.forEach((product: Product) => {
        if(product.supplierName == supplierName){
          this.servProduct.deleteProduct(product.id).subscribe();
        }
      });
    });
    return this.http.delete<Supplier>(this.url + id);
  }

  public updateSupplier(supplier: Supplier): Observable<Supplier>{
    return this.http.put<Supplier>(this.url + supplier.id, supplier);
  }

  public getIndustries(): Observable<any>{
    return this.http.get<any>('http://localhost:3000/rubros/');
  }

  public addIndustry(string: string): Observable<any>{
    return this.http.post<any>('http://localhost:3000/rubros/', {"rubro": string});
  }

  public login: boolean = false;
}

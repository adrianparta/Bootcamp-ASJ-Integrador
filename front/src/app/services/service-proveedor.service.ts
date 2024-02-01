import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proveedor } from '../models/proveedor';
import { Pais } from '../models/pais';
import { Provincia } from '../models/provincia';
import { Rubro } from '../models/rubro';
import { Iva } from '../models/iva';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService{

  url = 'http://localhost:8080/proveedores/';


  obtenerPaises(): Observable<Pais[]>{
    return this.http.get<Pais[]>('http://localhost:8080/paises/');
  }
  obtenerProvincias(paisId: number): Observable<Provincia[]>{
    return this.http.get<Provincia[]>('http://localhost:8080/provincias/' + paisId);
    
  }

  constructor(private http: HttpClient) {}

  public obtenerProveedores(): Observable<Proveedor[]>{
    return this.http.get<Proveedor[]>(this.url);
  }

  public obtenerProveedoresPorEstado(estado: boolean): Observable<Proveedor[]>{
    return this.http.get<Proveedor[]>(this.url + 'estado/' + estado);
  }

  public obtenerProveedor(id: number): Observable<Proveedor>{
    return this.http.get<Proveedor>(this.url + id);
  }
  
  public agregarProveedor(proveedor: Proveedor): Observable<Proveedor>{
    return this.http.post<Proveedor>(this.url, proveedor);
  }

  public modificarEstadoProveedor(id: number | undefined): Observable<Proveedor>{
    return this.http.put<Proveedor>(this.url + id + '/estado', {});
  }

  public modificarProveedor(proveedor: Proveedor): Observable<Proveedor>{
    return this.http.put<Proveedor>(this.url + proveedor.id, proveedor);
  }

  public obtenerRubros(): Observable<any>{
    return this.http.get<any>('http://localhost:8080/rubros/');
  }

  public agregarRubro(nombre: string): Observable<Rubro>{
    let rubro: Rubro = {
      rubro: nombre
    }
    console.log(rubro);
    
    return this.http.post<Rubro>('http://localhost:8080/rubros/', rubro);
  }

  public obtenerIvas(): Observable<Iva[]>{
    let aux = this.http.get<Iva[]>('http://localhost:8080/ivas/');
    return aux;
    
  }

  public login: boolean = false;
}

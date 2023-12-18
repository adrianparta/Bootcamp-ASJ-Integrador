import { Injectable } from '@angular/core';
import { proveedores } from '../data/proveedores';

@Injectable({
  providedIn: 'root'
})
export class ServiceProveedorService{

  agregarProveedor(proveedor: any){
    proveedores.push(proveedor);
  }

  public getProveedores(){
    return proveedores;
  }
}

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

  public getSingleProveedor(id: string): any{
    return proveedores.find((proveedor) => proveedor.id === +id);
  }

  public deleteSupplier(id: number){
    let idToDelete = proveedores.findIndex((proveedor) => proveedor.id === id);
    proveedores.splice(idToDelete, 1);
  }

  public updateSupplier(supplier: any){
    let index = proveedores.findIndex((sup) => sup.id === supplier.id);
    proveedores[index] = supplier;
  }

  public getLastId(){
    return proveedores[proveedores.length - 1].id;
  }
}

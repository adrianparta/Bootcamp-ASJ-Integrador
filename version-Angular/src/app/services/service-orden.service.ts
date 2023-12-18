import { Injectable } from '@angular/core';
import { ordenes } from '../data/ordenes';

@Injectable({
  providedIn: 'root'
})
export class ServiceOrdenService {

  agregarOrden(orden: any){
    ordenes.push(orden);
  }

  public getOrdenes(){
    return ordenes;
  }
}

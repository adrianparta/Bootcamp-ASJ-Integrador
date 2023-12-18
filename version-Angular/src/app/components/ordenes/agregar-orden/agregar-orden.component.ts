import { Component } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';

@Component({
  selector: 'app-agregar-orden',
  templateUrl: './agregar-orden.component.html',
  styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent {

  constructor(public serv: ServiceOrdenService) { }

  orden!: any;

  id!: string;
  numeroOrden!: string;
  fechaEmision!: string;
  fechaEntrega!: string;
  calle!: string;
  numero!: string;
  codigoPostal!: string;
  pais!: string;
  provincia!: string;
  localidad!: string;
  proveedor!: string;
  productos!: string;
  cantidad!: string;
  total!: string;

  cargarOrden(){
    this.orden = {
      id: this.id,
      numeroOrden: this.numeroOrden,
      fechaEmision: this.fechaEmision,
      fechaEntrega: this.fechaEntrega,
      calle: this.calle,
      numero: this.numero,
      codigoPostal: this.codigoPostal,
      pais: this.pais,
      provincia: this.provincia,
      localidad: this.localidad,
      proveedor: this.proveedor,
      productos: this.productos,
      cantidad: this.cantidad,
      total: this.total
    }    
    return this.orden;  
  }

  agregar(){
    this.serv.agregarOrden(this.cargarOrden());
  }
}

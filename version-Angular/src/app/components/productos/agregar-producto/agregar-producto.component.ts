import { Component } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent {

  constructor(public serv: ServiceProductoService) { }

  producto!: any;

  id!: string;
  proveedor!: string;
  codigo!: string;
  categoria!: string;
  nombre!: string;
  descripcion!: string;
  precio!: string;



  cargarProducto(){
    this.producto = {
      id: this.id,
      proveedor: this.proveedor,
      codigo: this.codigo,
      categoria: this.categoria,
      nombre: this.nombre,
      descripcion: this.descripcion,
      precio: this.precio
    }    
    return this.producto;  
  }

  agregar(){
    this.serv.agregarProducto(this.cargarProducto());
  }
}

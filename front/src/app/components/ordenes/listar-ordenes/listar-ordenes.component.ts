import { Component } from '@angular/core';
import { ServiceOrdenService as OrdenService } from '../../../services/service-orden.service';
import { Orden } from '../../../models/orden';
import { ProveedorService } from '../../../services/service-proveedor.service';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrl: './listar-ordenes.component.css'
})
export class ListarOrdenesComponent {

  ordenes: Orden[] = [];
  filtro: string = '';

  constructor(public ordenService: OrdenService, public proveedorService: ProveedorService){
  }  

  ngOnInit() {
    this.obtenerOrdenes();
    this.filtro = '';
  }

  obtenerOrdenes(){
    this.ordenService.obtenerOrdenes().subscribe((data: Orden[]) => {
      this.ordenes = data;
    });
  }

  modificarEstadoOrden(id: number){
    //todo: implementar sweetalert2, para confirmar la modificación del estado de la orden

    this.ordenService.modificarEstadoOrden(id || undefined).subscribe(()=>{
      this.obtenerOrdenes();
    });
  }
}

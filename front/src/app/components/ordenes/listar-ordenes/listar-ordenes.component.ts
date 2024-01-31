import { Component } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';
import { Orden } from '../../../models/orden';
import { ProveedorService } from '../../../services/service-proveedor.service';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrl: './listar-ordenes.component.css'
})
export class ListarOrdenesComponent {

  datos!: Orden[];
  filterText: string = '';

  constructor(public serv: ServiceOrdenService, public servSupplier: ProveedorService){
  }  

  ngOnInit() {
    this.getOrders();
    this.filterText = '';
  }

  getOrders(){
    this.serv.getOrders().subscribe((data: any[]) => {
      this.datos = data;
    });
  }

  cancelOrder(order: Orden){    
    if(confirm('¿Está seguro que desea cancelar la orden?')){
      order.estado = true;
      this.serv.updateOrder(order).subscribe(()=>{
        this.getOrders();
      });
    }
  }
}

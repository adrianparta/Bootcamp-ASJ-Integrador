import { Component } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';
import { Order } from '../../../models/orden';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrl: './listar-ordenes.component.css'
})
export class ListarOrdenesComponent {

  datos!: Order[];
  filterText: string = '';

  constructor(public serv: ServiceOrdenService, public servSupplier: ServiceProveedorService){
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

  cancelOrder(order: Order){    
    if(confirm('¿Está seguro que desea cancelar la orden?')){
      order.status = 'Cancelado';
      this.serv.updateOrder(order).subscribe(()=>{
        this.getOrders();
      });
    }
  }
}

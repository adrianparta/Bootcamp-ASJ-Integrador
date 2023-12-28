import { Component } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';
import { Order } from '../../../models/orders';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrl: './listar-ordenes.component.css'
})
export class ListarOrdenesComponent {

  datos!: Order[];

  constructor(public serv: ServiceOrdenService){
  }  

  ngOnInit() {
    this.serv.getOrders().subscribe((data: any[])=>{
      this.datos = data;
    });
  }
}

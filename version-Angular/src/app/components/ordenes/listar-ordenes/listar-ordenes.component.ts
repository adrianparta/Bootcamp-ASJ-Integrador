import { Component } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrl: './listar-ordenes.component.css'
})
export class ListarOrdenesComponent {

  datos!: any[];

  constructor(public serv: ServiceOrdenService){
  }  

  ngOnInit() {
    this.datos = this.serv.getOrdenes();
  }
}

import { Component, OnInit } from '@angular/core';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';

@Component({
  selector: 'app-listar-proveedores',
  templateUrl: './listar-proveedores.component.html',
  styleUrl: './listar-proveedores.component.css'
})
export class ListarProveedoresComponent implements OnInit {

  datos!: any[];

  constructor(public serv: ServiceProveedorService){
  }  

  getProveedores(){
    this.serv.getProveedores().subscribe((data: any[]) => {
      this.datos = data;
    });
  }
  
  ngOnInit() {
    this.getProveedores();
  }

  deleteSupplier(id: number){
    this.serv.deleteSupplier(id).subscribe((data: any[]) => {
      console.log(data);
      this.getProveedores();
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { Supplier } from '../../../models/supplier';

@Component({
  selector: 'app-listar-proveedores',
  templateUrl: './listar-proveedores.component.html',
  styleUrl: './listar-proveedores.component.css'
})
export class ListarProveedoresComponent implements OnInit {

  datos!: Supplier[];

  constructor(public serv: ServiceProveedorService){
  }  

  getSuppliers(){
    this.serv.getSuppliers().subscribe((data: Supplier[]) => {
      this.datos = data;
    });
  }
  
  ngOnInit() {
    this.getSuppliers();
  }

  deleteSupplier(id: number | undefined){
    this.serv.deleteSupplier(id).subscribe(() => {
      this.getSuppliers();
    });
  }
}

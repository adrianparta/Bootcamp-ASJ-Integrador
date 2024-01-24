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
  filterText: string = '';

  constructor(public serv: ServiceProveedorService){
  }  

  getSuppliers(){
    this.serv.getSuppliers().subscribe((data: Supplier[]) => {
      this.datos = data;
    });
  }
  
  ngOnInit() {
    this.getSuppliers();
    this.filterText = '';
  }

  deleteSupplier(id: number | undefined, supplierName: string){
    if(confirm('¿Está seguro que desea eliminar el proveedor?')){
      this.serv.deleteSupplier(id, supplierName).subscribe(() => {
        this.getSuppliers();
      });
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }
}

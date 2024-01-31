import { Component, OnInit } from '@angular/core';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { Proveedor } from '../../../models/proveedor';

@Component({
  selector: 'app-listar-proveedores',
  templateUrl: './listar-proveedores.component.html',
  styleUrl: './listar-proveedores.component.css'
})
export class ListarProveedoresComponent implements OnInit {

  proveedores!: Proveedor[];
  filtro: string = '';

  constructor(public proveedorService: ProveedorService){
  }  

  obtenerProveedores(){
    this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[]) => {
      this.proveedores = data;
    });
  }
  
  ngOnInit() {
    this.obtenerProveedores();
    this.filtro = '';
  }

  modificarEstadoProveedor(id: number | undefined){
    if(confirm('¿Está seguro que desea eliminar el proveedor?')){
      this.proveedorService.modificarEstadoProveedor(id).subscribe(() => {
        this.obtenerProveedores();
      });
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }
}

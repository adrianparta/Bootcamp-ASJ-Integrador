import { Component, OnInit} from '@angular/core';
import { ProveedorService } from '../../services/service-proveedor.service';
import { ProductoService} from '../../services/service-producto.service';
import { ServiceOrdenService as OrdenService } from '../../services/service-orden.service';
import { Proveedor } from '../../models/proveedor';
import { Producto } from '../../models/producto';
import { Orden } from '../../models/orden';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  proveedores: Proveedor[] = [];
  productos: Producto[] = [];
  ordenes: Orden[] = [];

  constructor(private proveedorService: ProveedorService, private productoService: ProductoService, private ordenService: OrdenService){}


  ngOnInit(): void {
    this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[]) => {
      this.proveedores = data;
    });

    this.productoService.obtenerProductosPorEstado(true).subscribe((data: Producto[]) => {
      this.productos = data;
    });

    this.ordenService.obtenerOrdenesPorEstado(true).subscribe((data: Orden[]) => {
      this.ordenes = data;
    });
  }
}

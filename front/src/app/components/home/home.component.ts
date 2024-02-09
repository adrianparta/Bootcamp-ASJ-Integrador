import { Component, OnInit} from '@angular/core';
import { ProveedorService } from '../../services/service-proveedor.service';
import { ProductoService} from '../../services/service-producto.service';
import { ServiceOrdenService as OrdenService } from '../../services/service-orden.service';
import { Proveedor } from '../../models/proveedor';
import { Producto } from '../../models/producto';
import { Orden } from '../../models/orden';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  proveedores: Proveedor[] = [];
  productos: Producto[] = [];
  ordenes: Orden[] = [];
  cantProveedores: number = 0;
  cantProductos: number = 0;
  cantOrdenes: number = 0;
  proveedorMasProductos!: {nombre: string, cantidad: number, id: number};
  proveedorMasVentas!: {nombre: string, cantidad: number, id: number};



  constructor(private proveedorService: ProveedorService, private productoService: ProductoService, private ordenService: OrdenService, private router: Router){}


  ngOnInit(): void {
    this.proveedorService.obtenerProveedores().subscribe((data: Proveedor[]) => {
      this.proveedores = data;
      this.productoService.obtenerProductos().subscribe((data: Producto[]) => {
        this.productos = data;
        this.ordenService.obtenerOrdenes().subscribe((data: Orden[]) => {
          this.ordenes = data;
          this.obtenerProveedorMasProductos();
          this.obtenerProveedorMasVentas();
        }, error => {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: JSON.stringify(error.error),
            timer: 2500,
            timerProgressBar: true,
            position: "top-end",
          });
          this.router.navigate(['/home']);
        });
      }, error => {
        Swal.fire({
          icon: "error",
          title: "Error",
          text: JSON.stringify(error.error),
          timer: 2500,
          timerProgressBar: true,
          position: "top-end",
        });
        this.router.navigate(['/home']);
      });
    }, error => {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: JSON.stringify(error.error),
        timer: 2500,
        timerProgressBar: true,
        position: "top-end",
      });
      this.router.navigate(['/home']);
    });

    this.obtenerCantidades();
  }

  obtenerCantidades(){
    this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[]) => {
      this.cantProveedores = data.length;
    }, error => {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: JSON.stringify(error.error),
        timer: 2500,
        timerProgressBar: true,
        position: "top-end",
      });
      this.router.navigate(['/home']);
    });

    this.productoService.obtenerProductosPorEstado(true).subscribe((data: Producto[]) => {
      this.cantProductos = data.length;
    }, error => {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: JSON.stringify(error.error),
        timer: 2500,
        timerProgressBar: true,
        position: "top-end",
      });
      this.router.navigate(['/home']);
    });

    this.ordenService.obtenerOrdenesPorEstado(true).subscribe((data: Orden[]) => {
      this.cantOrdenes = data.length;
    }, error => {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: JSON.stringify(error.error),
        timer: 2500,
        timerProgressBar: true,
        position: "top-end",
      });
      this.router.navigate(['/home']);
    });
  }

  obtenerProveedorMasProductos(){
    let proveedorMasProductos = {nombre: '', cantidad: 0, id: 0};
    this.proveedores.forEach((proveedor: Proveedor) => {
      let cantidad = 0;
      this.productos.forEach((producto: Producto) => {
        if(producto.proveedorId === proveedor.id && producto.estado === true){
          cantidad += 1;          
        }
      });
      if(cantidad > proveedorMasProductos.cantidad){
        proveedorMasProductos.cantidad = cantidad;
        proveedorMasProductos.nombre = proveedor.razonSocial;
        proveedorMasProductos.id = proveedor.id!;
      }
    });
    this.proveedorMasProductos = proveedorMasProductos;
  }

  localStorageProveedorMasProductos(){
    localStorage.setItem('filtroProveedor', this.proveedorMasProductos.id.toString());
  }

  obtenerProveedorMasVentas(){
    let proveedorMasVentas = {nombre: '', cantidad: 0, id: 0};
    this.proveedores.forEach((proveedor: Proveedor) => {
      let cantidad = 0;
      this.ordenes.forEach((orden: Orden) => {
        if(orden.proveedorId === proveedor.id && orden.estado === true){
          cantidad += 1;
        }
      });
      if(cantidad > proveedorMasVentas.cantidad){
        proveedorMasVentas.cantidad = cantidad;
        proveedorMasVentas.nombre = proveedor.razonSocial;
        proveedorMasVentas.id = proveedor.id!;
      }
    });
    this.proveedorMasVentas = proveedorMasVentas;

  }

  localStorageProveedorMasVentas(){
    localStorage.setItem('filtroProveedorVentas', this.proveedorMasProductos.id.toString());
  }
}

import { Component } from '@angular/core';
import { ServiceProductoService as ProductoService } from '../../../services/service-producto.service';
import { Producto } from '../../../models/producto';
import { ProveedorService } from '../../../services/service-proveedor.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-productos',
  templateUrl: './listar-productos.component.html',
  styleUrl: './listar-productos.component.css'
})
export class ListarProductosComponent {

  productos: Producto[] = [];
  filtro: string = '';

  constructor(public productoService: ProductoService, public proveedorService: ProveedorService){
  }

  ngOnInit() {
    this.obtenerProductos();
    this.filtro = '';
  }

  obtenerProductos(){
    this.productoService.obtenerProductos().subscribe((data: Producto[]) => {
      this.productos = data;
      this.ordenar();
    });
  }

  modificarEstadoProducto(id: number | undefined){
    Swal.fire({
      title: "¿Esta seguro que desea eliminar el producto?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, eliminar",
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Producto deshabilitado!",
          text: "Puede volver a habilitar el producto en la sección de productos deshabilitados.",
          icon: "success",
          allowEscapeKey: false,
          allowOutsideClick: false,
        }).then(()=>{
          this.productoService.modificarEstadoProducto(id).subscribe(()=>{
            this.obtenerProductos();
            this.ordenar();
          });
          
        });
      }
    });
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  ordenar(){
    this.productos.sort(function(a, b) {
      return a.nombre.localeCompare(b.nombre);
    });
  }

}

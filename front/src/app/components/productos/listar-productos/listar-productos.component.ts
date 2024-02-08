import { Component } from '@angular/core';
import { ProductoService as ProductoService } from '../../../services/service-producto.service';
import { Producto } from '../../../models/producto';
import { ProveedorService } from '../../../services/service-proveedor.service';
import Swal from 'sweetalert2';
import { Categoria } from '../../../models/categoria';
import { Proveedor } from '../../../models/proveedor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-productos',
  templateUrl: './listar-productos.component.html',
  styleUrl: './listar-productos.component.css'
})
export class ListarProductosComponent {

  productos: Producto[] = [];
  filtro: string = '';
  categorias: Categoria[] = [];
  filtroCategoria: number = 0;
  estado: boolean = true;
  ascdesc: string = '';
  proveedores: Proveedor[] = [];

  constructor(public productoService: ProductoService, public proveedorService: ProveedorService, private router: Router){
  }

  ngOnInit() {
    this.ascdesc = '';
    this.estado = true;
    this.filtroCategoria = 0;
    this.obtenerProveedores();
    this.obtenerProductos();
    this.filtro = '';
    this.productoService.obtenerCategorias().subscribe((data: Categoria[])=>{
      this.categorias = data;
      this.categorias.sort((a, b) => {
        if (a.estado && !b.estado) {
            return -1;
        }
        else if (!a.estado && b.estado) {
            return 1;
        }
        else {
            return 0;
        }
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

  }

  obtenerProductos(){
    this.productoService.obtenerProductosPorEstado(this.estado).subscribe((data: Producto[]) => {
      this.productos = data;
      this.ordenar();
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

  obtenerProveedores(){
    this.proveedorService.obtenerProveedores().subscribe((data: Proveedor[])=>{
      this.proveedores = data;
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

  modificarEstadoProducto(producto: Producto){
    let estado = (producto.estado) ? "deshabilitar" : "habilitar";
    Swal.fire({
      title: "¿Esta seguro que desea " + estado + " el producto " + producto.nombre +"?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, " + estado,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Listo!",
          text: "Si hubo una confusion, siempre puedes modificar el estado del producto",
          icon: "success",
          allowEscapeKey: false,
          allowOutsideClick: false,
          timer: 2000,
          timerProgressBar: true,
          position: "top-end",
        }).then(()=>{
          this.productoService.modificarEstadoProducto(producto.id).subscribe(()=>{
            this.obtenerProductos();
            this.ordenar();
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

    if(this.ascdesc == 'asc'){
      this.productos.sort(function (a, b) {
        return a.precio - b.precio;
      });
    }
    if(this.ascdesc == 'desc'){
      this.productos.sort(function (a, b) {
        return b.precio - a.precio;
      });
    }
  }

  modificarAscdesc(texto: string){
    this.ascdesc = texto;
    this.ordenar();
  }

  onSelectfiltroCategoria(){
    if(this.filtroCategoria == 0){
      this.obtenerProductos();
    }
    else{
      this.productoService.obtenerProductosPorCategoria(this.filtroCategoria, this.estado).subscribe((data: Producto[])=>{
        this.productos = data;
        this.ordenar();
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
  }

  activosOEliminados(estado: boolean){
    this.estado = estado;
    this.filtro = '';
    this.filtroCategoria = 0;
    this.obtenerProductos();
  }

  proveedorDesactivado(id: number){
    let proveedor = this.proveedores.find((proveedor) => proveedor.id == id);    
    return !proveedor?.estado;

  }

  buscarCategoria(producto: Producto){
    return this.categorias.some(objeto => objeto.id === producto.categoriaId && !objeto.estado)
  }

}

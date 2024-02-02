import { Component } from '@angular/core';
import { ServiceProductoService as ProductoService } from '../../../services/service-producto.service';
import { Producto } from '../../../models/producto';
import { ProveedorService } from '../../../services/service-proveedor.service';
import Swal from 'sweetalert2';
import { Categoria } from '../../../models/categoria';

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
  ascdesc: string = 'desc';

  constructor(public productoService: ProductoService, public proveedorService: ProveedorService){
  }

  ngOnInit() {
    this.estado = true;
    this.filtroCategoria = 0;
    this.obtenerProductos();
    this.filtro = '';
    this.productoService.obtenerCategoriasActivas().subscribe((data: Categoria[])=>{
      this.categorias = data;
    });

  }

  obtenerProductos(){
    this.productoService.obtenerProductosPorEstado(this.estado).subscribe((data: Producto[]) => {
      this.productos = data;
      this.ordenar();
    });
  }

  modificarEstadoProducto(producto: Producto){
    let estado = (producto.estado) ? "deshabilitar" : "habilitar";
    Swal.fire({
      title: "¿Esta seguro que desea " + estado + " el producto?",
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
        }).then(()=>{
          this.productoService.modificarEstadoProducto(producto.id).subscribe(()=>{
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
    this.productoService.obtenerProductosPorCategoria(this.filtroCategoria, this.estado).subscribe((data: Producto[])=>{
      this.productos = data;
      this.ordenar();
    });
  }

  activosOEliminados(estado: boolean){
    this.estado = estado;
    this.filtro = '';
    this.filtroCategoria = 0;
    this.obtenerProductos();
  }

}

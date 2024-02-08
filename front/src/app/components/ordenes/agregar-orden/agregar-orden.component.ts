import { Component, OnInit } from '@angular/core';
import { ServiceOrdenService as OrdenService } from '../../../services/service-orden.service';
import { Orden } from '../../../models/orden';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { ProductoService } from '../../../services/service-producto.service';
import { Proveedor } from '../../../models/proveedor';
import { Producto } from '../../../models/producto';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Detalle } from '../../../models/detalle';

@Component({
  selector: 'app-agregar-orden',
  templateUrl: './agregar-orden.component.html',
  styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent implements OnInit{

  constructor(public ordenService: OrdenService, private router: Router,private route: ActivatedRoute, public proveedorService: ProveedorService, public productoService: ProductoService) { }

  fechaFormateada: string = '';
  fechaActual = new Date();
  fechaManana = new Date(this.fechaActual.setDate(this.fechaActual.getDate() + 1));
  fechaMinima = this.fechaManana.toISOString().split('T')[0];
  agregarODetalles!:string;
  proveedores: Proveedor[] = [];
  productos!: Producto[];
  productoId!: number; 
  mostrarErrores!: boolean;
  cantidad!: number;
  detalles!:number;
  id!:number;
  url!: string;
  fechaEmision: string = new Date().toLocaleDateString('es-AR', {day: '2-digit', month: '2-digit', year: 'numeric'});
  orden!: Orden;
  
  comprobarFecha(){
    let fechanueva = new Date(this.orden.fechaEntrega);
    let fecha = new Date();
    return fechanueva.getTime() < fecha.getTime();
  }

  ngOnInit(): void {
    this.orden = {
      fechaEmision: new Date(),
      fechaEntrega: new Date(),
      info: '',
      proveedorId: 0,
      detalles: []
    };
    this.agregarODetalles = 'Agregar';
    this.productoId = 0;
    this.cantidad = 1;
    this.url = '';
    this.orden.total = 0;
    this.detalles = (this.router.routerState.snapshot.url.substring(1).includes('detalles')) ? 1 : 0;
    this.id = parseInt(this.route.snapshot.params['id']);
    this.url = '';
    this.mostrarErrores = false;
    if(this.detalles != 0){
      this.agregarODetalles = 'Detalles de la';
        this.ordenService.obtenerOrden(this.id).subscribe((data: Orden)=>{
          this.orden = data;          
          this.proveedorService.obtenerProveedor(this.orden.proveedorId).subscribe((data: Proveedor)=>{
            this.proveedores.push(data);
            this.url = data.urlImagen;
          });
          if(this.id != 0){
          this.fechaFormateada = this.orden.fechaEntrega.toString().split('T')[0];
          }
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

    this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
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

  agregar(formulario:any){
    if(formulario.valid && new Date(this.orden.fechaEntrega) >= new Date() && this.orden.info.length > 9 && this.orden.total! > 0){
      this.orden.estado = true;
      this.ordenService.agregarOrden(this.orden).subscribe();
      Swal.fire({
        title: 'Orden agregada con éxito',
        icon: 'success',
        confirmButtonText: 'OK',
        position: 'top-end',
        timer: 2000,
        timerProgressBar: true,
        allowEscapeKey: false,
        allowOutsideClick: false,
      }).then(()=>{
        this.router.navigate(['/ordenes']);
      });
    }else{
      this.mostrarErrores = true;
    }
  }

  onSelectProveedor(proveedor: any){
    let proveedorId = proveedor.target.value
    this.proveedorService.obtenerProveedor(proveedorId).subscribe((data: Proveedor)=>{
      this.url = data.urlImagen;
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
    this.productoService.obtenerProductosPorProveedor(proveedorId).subscribe((data: Producto[])=>{
      this.productos = data;      
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

  agregarProducto(){
    if(this.productoId != 0){

      let productoSeleccionado: Producto | undefined;
      productoSeleccionado = this.productos.find((producto: Producto)=>{
      return producto.id == this.productoId;
    });
    
    let index = this.orden.detalles.findIndex((detalle: Detalle)=>{                
      return detalle.productoId == productoSeleccionado?.id; 
    });
    
    if (index != -1) {
      this.orden.detalles[index].cantidad += this.cantidad;
    } else {
      this.orden.detalles.push({  
        cantidad: this.cantidad,
        precio_unitario: productoSeleccionado?.precio,
        productoId: productoSeleccionado?.id ?? 0,
        producto: productoSeleccionado?.nombre
      });        
    }
    this.calcularTotal();
    this.cantidad = 1;
    this.productoId = 0;
    }
    
  }
  
  calcularTotal(){
    this.orden.total = 0;
    this.orden.detalles.forEach((detalle: Detalle) => {
      this.orden.total! += (detalle?.precio_unitario ?? 0) * detalle.cantidad;
    });  
  }

  deleteProduct(id: any){
    this.orden.detalles = this.orden.detalles.filter(objeto => objeto.productoId !== id);
    this.calcularTotal();
  }

  limpiarDetalles(){
    this.orden.fechaEntrega = new Date();
    this.orden.info = '';
    this.orden.proveedorId = 0;
    
    this.orden.detalles = [];
    this.calcularTotal();
  }

  activarOrden(){
    this.orden.estado = true;
    this.ordenService.modificarEstadoOrden(this.orden.id).subscribe(()=>{
      Swal.fire({
        title: 'Orden activada con éxito',
        icon: 'success',
        timer: 2000,
        timerProgressBar: true,
        position: 'top-end',
        confirmButtonText: 'OK',
        allowEscapeKey: false,
        allowOutsideClick: false,
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

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }
}

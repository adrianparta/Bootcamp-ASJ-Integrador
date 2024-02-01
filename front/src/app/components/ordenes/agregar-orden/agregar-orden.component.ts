import { Component, OnInit } from '@angular/core';
import { ServiceOrdenService as OrdenService } from '../../../services/service-orden.service';
import { Orden } from '../../../models/orden';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { ServiceProductoService } from '../../../services/service-producto.service';
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

  constructor(public ordenService: OrdenService, private router: Router,private route: ActivatedRoute, public proveedorService: ProveedorService, public productoService: ServiceProductoService) { }

  fechaFormateada: string = '';
  fechaActual = new Date()
  fechaManana = new Date(this.fechaActual.setDate(this.fechaActual.getDate() + 1));
  fechaMinima = this.fechaManana.toISOString().split('T')[0];
  agregarODetalles:string = 'Agregar';
  proveedores: Proveedor[] = [];
  productos!: Producto[];
  productoId: number = 0; 
  mostrarErrores!: boolean;
  cantidad: number = 1;
  detalles?:number;
  id:number = -1;
  fechaEmision: string = new Date().toLocaleDateString('es-AR', {day: '2-digit', month: '2-digit', year: 'numeric'});
  orden: Orden = {
    fechaEmision: new Date(),
    fechaEntrega: new Date(),
    info: '',
    proveedorId: 0,
    detalles: []
  }
  
  comprobarFecha(){

    let fechanueva = new Date(this.orden.fechaEntrega);
    let fecha = new Date();
    
    return fechanueva.getTime() < fecha.getTime();
  }

  ngOnInit(): void {
    
    this.orden.total = 0;
    this.detalles = parseInt(this.route.snapshot.params['details']);
    this.id = parseInt(this.route.snapshot.params['id']);

    this.mostrarErrores = false;
    if(this.detalles!=0){
      this.agregarODetalles = 'Detalles de la';
      this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
        this.proveedores = data;
        this.ordenService.obtenerOrden(this.id).subscribe((data: Orden)=>{
          this.orden = data;
          if(this.id!=-1){
            console.log(this.orden.fechaEntrega);
            
          this.fechaFormateada = this.orden.fechaEntrega.toString().split('T')[0];
          }
        }); 
      });
    }

    this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
      this.proveedores = data;
    });
  }

  agregar(formulario:any){
    if(formulario.valid && this.orden.fechaEntrega>=new Date() || this.orden.total!=0){
      this.orden.estado = true;
      this.ordenService.agregarOrden(this.orden).subscribe();
      Swal.fire({
        title: 'Orden agregada con éxito',
        icon: 'success',
        confirmButtonText: 'OK',
        allowEscapeKey: false,
        allowOutsideClick: false,
      }).then(()=>{
        this.router.navigate(['/listar-ordenes']);
      });
    }else{
      this.mostrarErrores = true;      
    }
  }

  onSelectProveedor(proveedor: any){
    let proveedorId = proveedor.target.value
    this.productoService.obtenerProductosPorProveedor(proveedorId).subscribe((data: Producto[])=>{
      this.productos = data;      
    });  
  }

  agregarProducto(){
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
                                  precioUnitario: productoSeleccionado?.precio,
                                  productoId: productoSeleccionado?.id ?? 0,
                                  producto: productoSeleccionado?.nombre
                                });        
    }
    this.calcularTotal();
    this.cantidad = 1;
    this.productoId = 0;
  }

  calcularTotal(){
    this.orden.total = 0;
    this.orden.detalles.forEach((detalle: Detalle) => {
      this.orden.total! += (detalle?.precioUnitario ?? 0) * detalle.cantidad;
    });  
  }

  deleteProduct(id: any){
    this.orden.detalles = this.orden.detalles.filter(objeto => objeto.productoId !== id);
    this.calcularTotal();
  }
}

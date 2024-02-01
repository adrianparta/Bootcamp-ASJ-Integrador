import { Component, OnInit } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';
import { Orden } from '../../../models/orden';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Proveedor } from '../../../models/proveedor';
import { Producto } from '../../../models/producto';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar-orden',
  templateUrl: './agregar-orden.component.html',
  styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent implements OnInit{

  constructor(public serv: ServiceOrdenService, private router: Router,private route: ActivatedRoute, public servSupplier: ProveedorService, public servProduct: ServiceProductoService) { }

  fechaActual = new Date()
  agregarODetalles:string = 'Agregar'
  supplierList!: Proveedor[];
  productList!: Producto[];
  productIdSelected!: number;
  showErrors!: boolean;
  amountSelected: number = 1;
  blockSelectSupplier: boolean = false;
  toasts: boolean = false;
  details?:number;
  id:number = -1;
  fechaEmision: string = new Date().toLocaleDateString('es-AR', {day: '2-digit', month: '2-digit', year: 'numeric'});
  orden: Orden = {
    fechaEntrega: new Date(),
    info: '',
    proveedorId: 0,
    detalles: []
  }
  
  comprobar(){
    console.log(this.orden.fechaEntrega);
    console.log(this.fechaActual);
    
    console.log(this.orden.fechaEntrega<this.fechaActual);
  }

  ngOnInit(): void {
  this.details = parseInt(this.route.snapshot.params['details']);
  this.id = parseInt(this.route.snapshot.params['id']);

    this.showErrors = false;
    if(this.details!=0){
      this.agregarODetalles = 'Detalles de la';
      this.servSupplier.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
        this.supplierList = data;
        this.serv.obtenerOrden(this.id).subscribe((data: Orden)=>{
          this.orden = data;
        }); 
      });
    }

    this.servSupplier.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
      this.supplierList = data;
      this.servProduct.obtenerProductos().subscribe((data: Producto[])=>{
        this.productList = data.filter((product: Producto) => product.proveedorId == this.orden.proveedorId);
      });
    });
  }

  agregar(formulario:any){
    console.log("hola");
    
    if(formulario.valid && this.orden.fechaEntrega>=new Date() || this.orden.total!=0){
      this.orden.estado = true;
      this.serv.agregarOrden(this.orden).subscribe();
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
      this.showErrors = true;      
    }
  }

  onSelectSupplier(supplier: any){
    let SupplierName = supplier.target.value
    this.servProduct.obtenerProductos().subscribe((data)=>{
      this.productList = data.filter((product:any) => product.supplierName == SupplierName);      
    });  
  }

  addProduct(){
    let productSelected: Producto;
    this.servProduct.obtenerProducto(this.productIdSelected).subscribe((data: Producto)=>{
      productSelected = data;

      let index = this.orden.detalles.findIndex((objeto: any)=>{                
        return objeto.product.id === productSelected.id; 
      });
      
      if (index != -1) {
        this.orden.detalles[index].cantidad += this.amountSelected;
      } else {
        //todo agregar cantidad
        //this.orden.detalles.push({producto: productSelected.nombre, cantidad: this.amountSelected});
      }
      this.calcularTotal();
      this.amountSelected = 1;
      this.productIdSelected = 0;
    });
  }

  calcularTotal(){
    if(this.orden.detalles.length >= 1){
      this.blockSelectSupplier = true;
    }
    else{
      this.blockSelectSupplier = false;
    }
    this.orden.total = 0;
    this.orden.detalles.forEach((objeto: any) => {
      //todo agregar total
      //this.orden.total += objeto.product.price * objeto.quantity;
    });  
  }

  deleteProduct(id: any){
    this.orden.detalles = this.orden.detalles.filter(objeto => objeto.productoId !== id);
    this.calcularTotal();
  }
}

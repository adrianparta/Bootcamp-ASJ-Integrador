import { Component, OnInit } from '@angular/core';
import { ServiceOrdenService } from '../../../services/service-orden.service';
import { Order } from '../../../models/orders';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Supplier } from '../../../models/supplier';
import { Product } from '../../../models/products';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-agregar-orden',
  templateUrl: './agregar-orden.component.html',
  styleUrl: './agregar-orden.component.css'
})
export class AgregarOrdenComponent implements OnInit{

  constructor(public serv: ServiceOrdenService, private router: Router,private route: ActivatedRoute, public servSupplier: ServiceProveedorService, public servProduct: ServiceProductoService) { }

  fechaActual = new Date().toISOString().split('T')[0];
  agregarODetalles:string = 'Agregar'
  supplierList!: Supplier[];
  productList!: Product[];
  productIdSelected!: number;
  mostrarErrores!: boolean;
  showAlert: boolean = false;
  amountSelected: number = 1;
  blockSelectSupplier: boolean = false;
  toasts: boolean = false;
  details:number = parseInt(this.route.snapshot.params['details']);
  id:number = parseInt(this.route.snapshot.params['id']);
  orden: Order = {
    issueDate: new Date(),
    expectedDeliveryDate: new Date(),
    info: '',
    supplier: '',
    products: [],
    total: 0,
    status: ''
  }

  ngOnInit(): void {
    this.mostrarErrores = false;
    if(this.details!=0){
      this.agregarODetalles = 'Detalles de la';
      this.servSupplier.getSuppliers().subscribe((data: Supplier[])=>{
        this.supplierList = data;
        this.serv.getSingleOrder(this.id).subscribe((data: Order)=>{
          this.orden = data;
        }); 
      });
    }

    this.servSupplier.getSuppliers().subscribe((data: Supplier[])=>{
      this.supplierList = data;
      this.servProduct.getProducts().subscribe((data: Product[])=>{
        this.productList = data.filter((product: Product) => product.supplierName == this.orden.supplier);
      });
    });
  }

  agregar(formulario:any){
    console.log("hola");
    
    if(formulario.valid && this.orden.expectedDeliveryDate>=this.orden.issueDate || this.orden.total!=0){
      this.orden.status = 'Finalizado';
      this.serv.addOrder(this.orden).subscribe();
      this.showAlert = true;
    }else{
      this.mostrarErrores = true;      
    }
  }

  onSelectSupplier(supplier: any){
    let SupplierName = supplier.target.value
    this.servProduct.getProducts().subscribe((data)=>{
      this.productList = data.filter((product:any) => product.supplierName == SupplierName);      
    });  
  }

  addProduct(){
    let productSelected: Product;
    this.servProduct.getSingleProduct(this.productIdSelected).subscribe((data: Product)=>{
      productSelected = data;

      let index = this.orden.products.findIndex((objeto: any)=>{                
        return objeto.product.id === productSelected.id; 
      });
      
      if (index != -1) {
        this.orden.products[index].quantity += this.amountSelected;
      } else {
        this.orden.products.push({product: productSelected, quantity: this.amountSelected});
      }
      this.calcularTotal();
      this.amountSelected = 1;
      this.productIdSelected = 0;
    });
  }

  calcularTotal(){
    if(this.orden.products.length >= 1){
      this.blockSelectSupplier = true;
    }
    else{
      this.blockSelectSupplier = false;
    }
    this.orden.total = 0;
    this.orden.products.forEach((objeto: any) => {
      this.orden.total += objeto.product.price * objeto.quantity;
    });  
  }

  deleteProduct(id: any){
    this.orden.products = this.orden.products.filter(objeto => objeto.product.id !== id);
    this.calcularTotal();
  }
}

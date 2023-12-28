import { Component, OnInit } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Product } from '../../../models/products';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { Supplier } from '../../../models/supplier';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent implements OnInit{

  constructor(public serv: ServiceProductoService, private router: Router, public list: ServiceProveedorService, private route: ActivatedRoute){}

  id: number = parseInt(this.route.snapshot.params['id']);
  details: number = parseInt(this.route.snapshot.params['details']);
  title = 'Agregar';
  productList!: Product[];
  agregarOEditar = 'Agregar';
  mostrarErrores!: boolean;
  codigoRepetido = false;
  showAlert: boolean = false;

  product: Product = {
    supplierName: '',
    code: '',
    category: '',
    name: '',
    description: '',
    price: 1,
    img: '',
  }

  suppliersList!: Supplier[];
  categoriesList!: string[];

  ngOnInit(): void {
    this.list.getSuppliers().subscribe((data: Supplier[])=>{
      this.suppliersList = data;
    })
    this.serv.getCategories().subscribe((data: string[])=>{
      this.categoriesList = data;
    })

    if(this.id!=-1){
      this.title = 'Editar';
      this.agregarOEditar = 'Editar';
      this.serv.getSingleProduct(this.id).subscribe((data: Product)=>{
        this.product = data;
      })
    }

    this.serv.getProducts().subscribe((data: Product[])=>{
      this.productList = data;
      if(this.id!=-1){
        this.productList = this.productList?.filter((product: Product)=>product.id!=this.id);
      }
    });

    if(this.details==1){
      this.title = 'Detalles del';
    }
  }

  agregar(formulario: any){
    
    if(formulario.valid && !this.codigoRepetido){
      if(this.id==-1){
        this.serv.addProduct(this.product).subscribe();
      }else{
        this.serv.updateProduct(this.product).subscribe();
      }
      this.showAlert = true;
    }
    else{
      this.mostrarErrores = true;
    }
  }

  comprobarRepetido(inputCode: any){
    let invalid!: boolean;
    invalid = this.productList.some((product: Product)=> product.code==inputCode.value);
    invalid ? this.codigoRepetido = true : this.codigoRepetido = false;    
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }
}

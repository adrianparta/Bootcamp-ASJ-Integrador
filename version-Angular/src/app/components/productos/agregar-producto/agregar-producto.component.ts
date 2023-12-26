import { Component, OnInit } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Product } from '../../../models/products';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { Supplier } from '../../../models/supplier';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent implements OnInit{

  constructor(public serv: ServiceProductoService, public list: ServiceProveedorService){}

  product: Product = {
    supplierName: '',
    code: '',
    category: '',
    name: '',
    description: '',
    price: 0
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
  }

  agregar(){
    this.serv.addProduct(this.product).subscribe(() => {
    });
  }

  
}

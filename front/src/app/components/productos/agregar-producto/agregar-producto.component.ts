import { Component, OnInit } from '@angular/core';
import { ServiceProductoService } from '../../../services/service-producto.service';
import { Producto } from '../../../models/producto';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { Proveedor } from '../../../models/proveedor';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent implements OnInit{

  constructor(public serv: ServiceProductoService, private router: Router, public list: ProveedorService, private route: ActivatedRoute){}

  id?: number;
  details?: number;
  title = 'Agregar';
  productList!: Producto[];
  agregarOEditar = 'Agregar';
  showErrors!: boolean;
  codigoRepetido = false;
  newCategory = '';

  product: Producto = {
    codigo: '',
    nombre: '',
    descripcion: '',
    precio: 0,
    imagen_url: '',
    categoriaId: 0,
    proveedorId: 0
  }

  suppliersList!: Proveedor[];
  categoriesList!: any;

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.params['id']);
    this.details = parseInt(this.route.snapshot.params['details']);
    
    this.list.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
      this.suppliersList = data;
    })
    this.serv.getCategories().subscribe((data: string[])=>{
      this.categoriesList = data;
    })

    if(this.id!=-1){
      this.title = 'Editar';
      this.agregarOEditar = 'Editar';
      this.serv.getSingleProduct(this.id).subscribe((data: Producto)=>{
        this.product = data;
      })
    }

    this.serv.obtenerProductos().subscribe((data: Producto[])=>{
      this.productList = data;
      if(this.id!=-1){
        this.productList = this.productList?.filter((product: Producto)=>product.id!=this.id);
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
      formulario.reset();
      Swal.fire({
        title: 'Producto agregado con éxito',
        icon: 'success',
        confirmButtonText: 'OK',
        allowEscapeKey: false,
        allowOutsideClick: false,
      }).then(()=>{
        this.router.navigate(['/listar-productos']);
      });
    }
    else{
      this.showErrors = true;
    }
  }

  comprobarRepetido(inputCode: any){
    let invalid!: boolean;
    invalid = this.productList.some((producto: Producto)=> producto.codigo==inputCode.value);
    invalid ? this.codigoRepetido = true : this.codigoRepetido = false;    
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  addNewCategory(){    
    this.serv.addCategory(this.newCategory).subscribe(() =>{
      this.serv.getCategories().subscribe((data)=>{        
        this.categoriesList = data;
        this.product.categoria = this.categoriesList[this.categoriesList.length - 1].categoria;
      });
      this.newCategory = '';
    })
  }
}

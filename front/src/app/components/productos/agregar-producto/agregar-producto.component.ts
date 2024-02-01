import { Component, OnInit } from '@angular/core';
import { ServiceProductoService as ProductoService } from '../../../services/service-producto.service';
import { Producto } from '../../../models/producto';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { Proveedor } from '../../../models/proveedor';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Categoria } from '../../../models/categoria';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrl: './agregar-producto.component.css'
})
export class AgregarProductoComponent implements OnInit{

  constructor(public productoService: ProductoService, private router: Router, public proveedorService: ProveedorService, private route: ActivatedRoute){}

  id?: number;
  detalles?: number;
  titulo = 'Agregar';
  productos!: Producto[];
  agregarOEditar = 'Agregar';
  mostrarErrores!: boolean;
  codigoRepetido = false;
  nuevaCategoria = '';
  codigoRegex: RegExp = /^[A-Z]{2}[0-9]{3}$/;


  producto: Producto = {
    codigo: '',
    nombre: '',
    descripcion: '',
    precio: 1,
    imagen_url: '',
    categoriaId: 0,
    proveedorId: 0
  }

  proveedores!: Proveedor[];
  categorias!: any;

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.params['id']);
    this.detalles = parseInt(this.route.snapshot.params['details']);
    
    this.proveedorService.obtenerProveedoresPorEstado(true).subscribe((data: Proveedor[])=>{
      this.proveedores = data;
    })
    this.productoService.obtenerCategorias().subscribe((data: Categoria[])=>{
      this.categorias = data;
    })

    if(this.id!=-1){
      this.titulo = 'Editar';
      this.agregarOEditar = 'Editar';
      this.productoService.obtenerProducto(this.id).subscribe((data: Producto)=>{
        this.producto = data;
      })
    }

    this.productoService.obtenerProductos().subscribe((data: Producto[])=>{
      this.productos = data;
      if(this.id!=-1){
        this.productos = this.productos?.filter((product: Producto)=>product.id!=this.id);
      }
    });

    if(this.detalles==1){
      this.titulo = 'Detalles del';
    }
  }

  agregar(formulario: any){
    
    if(formulario.valid && !this.codigoRepetido && this.validarCodigo()){
      if(this.id==-1){
        this.productoService.agregarProducto(this.producto).subscribe(()=>{
          this.redirigir('Producto agregado con éxito', formulario);
        },  
        (error: HttpErrorResponse) => {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: JSON.stringify(error.error),
          });
        });
      }else{
        this.productoService.modificarProducto(this.producto).subscribe(()=>{
          this.redirigir('Producto modificado con éxito', formulario);
        },  
        (error: HttpErrorResponse) => {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: JSON.stringify(error.error),
          });
        });
      }
      
    }
    else{
      this.mostrarErrores = true;
    }
  }

  redirigir(titulo: string, formulario: any){
    formulario.reset();
      Swal.fire({
        title: titulo,
        icon: 'success',
        confirmButtonText: 'OK',
        allowEscapeKey: false,
        allowOutsideClick: false,
      }).then(()=>{
        this.router.navigate(['/listar-productos']);
      });
  }

  comprobarRepetido(inputCode: any){
    let invalid!: boolean;
    invalid = this.productos.some((producto: Producto)=> producto.codigo==inputCode.value);
    invalid ? this.codigoRepetido = true : this.codigoRepetido = false;    
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  agregarCategoria(){   
    this.productoService.agregarCategoria(this.nuevaCategoria).subscribe(() =>{
      this.productoService.obtenerCategorias().subscribe((data: Categoria[])=>{        
        this.categorias = data;
        this.producto.categoria = this.categorias[this.categorias.length - 1].categoria;
      });
      this.nuevaCategoria = '';
    })
  }

  validarCodigo(){
    const valido = this.codigoRegex.test(this.producto.codigo);
    return valido;
  }

  toUpperCase(event: any) {
    const newValue = event.target.value.toUpperCase();
    event.target.value = newValue;
  }
}

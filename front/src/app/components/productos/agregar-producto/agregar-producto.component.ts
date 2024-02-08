import { Component, OnInit } from '@angular/core';
import { ProductoService as ProductoService } from '../../../services/service-producto.service';
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
  titulo!: string;
  productos!: Producto[];
  agregarOEditar!: string;
  mostrarErrores!: boolean;
  codigoRepetido!: boolean;
  nuevaCategoria!: string;
  codigoRegex: RegExp = /^[A-Z]{2}[0-9]{3}$/;
  filtroCategorias!: string;
  categoria!: Categoria
  producto!: Producto;

  proveedores!: Proveedor[];
  categoriasActivas!: Categoria[];
  categorias!: Categoria[];
  
  ngOnInit(): void {
    this.titulo = 'Agregar';
    this.agregarOEditar = 'Agregar';
    this.codigoRepetido = false;
    this.nuevaCategoria = '';
    this.filtroCategorias = '';
    this.categoria = {
      categoria: '',
    };
    this.producto = {
      codigo: '',
      nombre: '',
      descripcion: '',
      precio: 1,
      imagen_url: '',
      categoriaId: 0,
      proveedorId: 0
    }
    this.id = parseInt(this.route.snapshot.params['id']);
    this.detalles = (this.router.routerState.snapshot.url.substring(1).includes('detalles')) ? 1 : 0;
    
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
    this.obtenerCategoriasActivas();

    if(this.id!=0){
      this.titulo = 'Editar';
      this.agregarOEditar = 'Editar';
      this.productoService.obtenerProducto(this.id).subscribe((data: Producto)=>{
        this.producto = data;
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

    this.productoService.obtenerProductos().subscribe((data: Producto[])=>{
      this.productos = data;
      if(this.id!=0){
        this.productos = this.productos?.filter((product: Producto)=>product.id!=this.id);
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

    if(this.detalles==1){
      this.titulo = 'Detalles del';
    }
  }

  obtenerCategoriasActivas(){
    this.productoService.obtenerCategoriasActivas().subscribe((data: Categoria[])=>{        
      this.categoriasActivas = data;
      this.categoriasActivas.sort((a, b) => {
        return a.categoria.localeCompare(b.categoria);
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
    this.nuevaCategoria = '';
    this.filtroCategorias = '';
  }

  agregar(formulario: any){
    
    if(formulario.valid && !this.codigoRepetido && this.validarCodigo()){
      if(this.id==0){
        this.productoService.agregarProducto(this.producto).subscribe(()=>{
          this.redirigir('Producto agregado con éxito', formulario);
        },  
        (error: HttpErrorResponse) => {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: JSON.stringify(error.error),
            timer: 2500,
            timerProgressBar: true,
            position: "top-end",
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
            timer: 2500,
            timerProgressBar: true,
            position: "top-end",
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
        timer: 2000,
        timerProgressBar: true,
        position: "top-end",
      }).then(()=>{
        this.router.navigate(['/productos']);
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
    if(this.nuevaCategoria.length>0){
      this.productoService.agregarCategoria(this.nuevaCategoria).subscribe(() =>{
        this.obtenerCategoriasActivas();
        this.obtenerCategorias();
        Swal.fire({
          title: 'Categoría agregada con éxito',
          icon: 'success',
          confirmButtonText: 'OK',
          allowEscapeKey: true,
          allowOutsideClick: true,
          timer: 2000,
          timerProgressBar: true,
          position: "top-end",
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
      })
    }
    else{
      Swal.fire({
        icon: "error",
        title: "Error",
        text: "El campo no puede estar vacío",
        timer: 2500,
        timerProgressBar: true,
        position: "top-end",
      });
    }
    this.nuevaCategoria = '';
  }

  validarCodigo(){
    const valido = this.codigoRegex.test(this.producto.codigo);
    return valido;
  }

  toUpperCase(event: any) {
    const newValue = event.target.value.toUpperCase();
    event.target.value = newValue;
  }

  obtenerCategorias(){
    this.productoService.obtenerCategorias().subscribe((data: Categoria[])=>{
      this.categorias = data;

      this.categorias.sort((a, b) => {
        return a.categoria.localeCompare(b.categoria);
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

  modificarCategoria(categoria: Categoria){
    this.productoService.modificarCategoria(categoria).subscribe(()=>{
      this.obtenerCategorias();
      Swal.fire({
        title: 'Categoria modificada con éxito',
        icon: 'success',
        confirmButtonText: 'OK',
        allowEscapeKey: true,
        allowOutsideClick: true,
        timer: 2000,
        timerProgressBar: true,
        position: "top-end",
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
    });
    this.obtenerCategorias();
  }

  modificarEstadoCategoria(categoria: Categoria){
    categoria.estado = !categoria.estado;
    this.modificarCategoria(categoria);
  }

  buscarCategoria(){
    if(this.id!=0){
      return !this.categoriasActivas.some(objeto => objeto.id == this.producto.categoriaId)
    }
    return false;
  }

  buscarProveedor(){
    if(this.id!=0){
      return !this.proveedores.some(objeto => objeto.id == this.producto.proveedorId)
    }
    return false;
  }  

  activarProducto(){
    Swal.fire({
      title: "¿Esta seguro que desea habilitar el producto?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, habilitar",
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Producto habilitado!",
          icon: "success",
          allowEscapeKey: false,
          allowOutsideClick: false,
          timer: 2000,
          timerProgressBar: true,
          position: "top-end",
        }).then(()=>{
          this.productoService.modificarEstadoProducto(this.producto.id).subscribe((data: Producto)=>{
            this.producto = data;
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

  limpiarFormulario(){
    this.producto = {
      codigo: '',
      nombre: '',
      descripcion: '',
      precio: 1,
      imagen_url: '',
      categoriaId: 0,
      proveedorId: 0
    }
  }
}

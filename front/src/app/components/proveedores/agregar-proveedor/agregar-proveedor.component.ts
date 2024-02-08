import { Component, OnInit} from '@angular/core';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Proveedor} from '../../../models/proveedor';
import Swal from 'sweetalert2';
import { Pais } from '../../../models/pais';
import { Provincia } from '../../../models/provincia';
import { Rubro } from '../../../models/rubro';
import { Iva } from '../../../models/iva';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-agregar-proveedor',
  templateUrl: './agregar-proveedor.component.html',
  styleUrl: './agregar-proveedor.component.css'
})
export class AgregarProveedorComponent implements OnInit{


  constructor(public proveedorService: ProveedorService, private route: ActivatedRoute, private router: Router) { }
  
  formularioValido!: boolean;
  mostrarErrores!: boolean;
  urlRegex: RegExp = /(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})/;
  emailRegex: RegExp = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
  cuitRegex: RegExp = /^(20|23|24|25|26|27|30|33|34)-?\d{8}-?\d$/;
  codigoRegex: RegExp = /^[A-Z]{2}[0-9]{3}$/;
  titulo!: string;
  agregarOEditar!: string;
  cuitRepetido!: boolean;
  codigoRepetido!: boolean;
  razonSocialRepetida!: boolean;
  id?:number;
  detalles?:number;
  paises!: Pais[];
  pais: number = 0;
  provincias!: Provincia[];
  localidades: any;
  inputDesactivado = false;
  rubrosActivos: Rubro[] = [];
  rubros!: Rubro[];
  proveedores!: Proveedor[];
  mostrarAlerta: boolean = false;
  nuevoRubro: string = '';
  paisID: number = 0;
  ivas!: Iva[];
  rubroAux: Rubro = {
    rubro: ''
  }
  rubro: Rubro = {
    rubro: ''
  }
  filtroRubros: string = '';

  proveedor: Proveedor = {
    codigo: '',
    razonSocial: '',
    web: '',
    email: '',
    telefono: '',
    calle: '',
    altura: '',
    codigoPostal: '',
    cuit: '',
    contactoNombre: '',
    contactoApellido: '',
    contactoTelefono: '',
    contactoEmail: '',
    contactoRol: '',
    localidad: '',
    urlImagen: '',
    provinciaId: 0,
    rubroId: 0,
    ivaId: 0
  }

  ngOnInit(): void {
    this.formularioValido = false;
    this.titulo = 'Agregar Proveedor';
    this.agregarOEditar = 'Agregar';
    this.filtroRubros = '';
    this.id = parseInt(this.route.snapshot.params['id']);
    this.detalles = (this.router.routerState.snapshot.url.substring(1).includes('detalles')) ? 1 : 0;
    this.mostrarErrores = false;
    this.proveedorService.obtenerPaises().subscribe((data: Pais[])=>{
      this.paises = data;
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

    this.obtenerRubrosActivos();

    this.proveedorService.obtenerIvas().subscribe((data: Iva[])=>{
      this.ivas = data;
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
    })

    if(this.id != 0){
      this.inputDesactivado = true;
      this.titulo = 'Editar Proveedor';
      this.agregarOEditar = 'Editar';
      this.proveedorService.obtenerProveedor(this.id).subscribe((data: Proveedor) => {
        this.proveedor = data;
        this.pais = this.paises.find((pais: Pais) => pais.pais == this.proveedor.pais)?.id || 0;
        this.onSelectPais();
        if(this.buscarRubro()){
          this.proveedorService.obtenerRubro(this.proveedor.rubroId).subscribe((data: Rubro) => {
            this.rubro = data;
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

    this.proveedorService.obtenerProveedores().subscribe((data: Proveedor[])=>{
      this.proveedores = data;
      if(this.id != 0){
        this.proveedores = this.proveedores?.filter((proveedor: Proveedor) => proveedor.id != this.id);
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
    
    if(this.detalles == 1){
      this.titulo = 'Detalles del Proveedor';
    }

  }

  redirigir(texto: string){
    Swal.fire({
      title: 'Proveedor ' + texto + ' con éxito',
      icon: 'success',
      confirmButtonText: 'OK',
      allowEscapeKey: false,
      allowOutsideClick: false,
      timer: 2000,
      timerProgressBar: true,
      position: "top-end",
    }).then(()=>{
      this.router.navigate(['/proveedores']);
    });
  }

  agregar(formulario: any){
    if(this.formularioValido && formulario.valid){
      if(this.id == 0){
        this.proveedorService.agregarProveedor(this.proveedor).subscribe(() => {
          this.redirigir('agregado');
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
      else{
        this.proveedorService.modificarProveedor(this.proveedor).subscribe( () => {
          this.redirigir('modificado');
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
    }
    else{
      this.mostrarErrores = true;
    }
  }

  onSelectPais() {
    this.proveedorService.obtenerProvincias(this.pais).subscribe((data: Provincia[])=>{
      this.provincias = data;
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

  validarURL(){
    const valid = this.urlRegex.test(this.proveedor.web);
    valid ? this.formularioValido = true : this.formularioValido = false;
    return valid;
  }

  validarEmail(value: string){
    const valid = this.emailRegex.test(value);
    valid ? this.formularioValido = true : this.formularioValido = false;
    return valid;
  }

  validarCUIT(){
    const valido = this.cuitRegex.test(this.proveedor.cuit);
    valido ? this.formularioValido = true : this.formularioValido = false;
    return valido;
  }

  validarCodigo(){
    const valido = this.codigoRegex.test(this.proveedor.codigo);
    valido ? this.formularioValido = true : this.formularioValido = false;
    return valido;
  }

  comprobarRepetido(value: string, key: string): void{    
    let valido!: boolean;
    
    switch(key){
      case 'cuit':
        valido = this.proveedores.some((proveedor: Proveedor) => proveedor.cuit == value);
        this.formularioValido = (valido) ? false : true;
        this.cuitRepetido = valido;
        break;
      case 'codigo':
        valido = this.proveedores.some((proveedor: Proveedor) => proveedor.codigo == value);
        this.formularioValido = (valido) ? false : true;
        this.codigoRepetido = valido;        
        break;
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  agregarRubro(){    
    if(this.nuevoRubro.length>0){
      this.proveedorService.agregarRubro(this.nuevoRubro).subscribe(() =>{
        this.obtenerRubros();
        Swal.fire({
          title: 'Rubro agregado con éxito',
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
    this.nuevoRubro = '';
  }

  toUpperCase(event: any) {
    const newValue = event.target.value.toUpperCase();
    event.target.value = newValue;
  }

  activarProveedor(){
    Swal.fire({
      title: "¿Esta seguro que desea habilitar el proveedor?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, habilitar",
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Proveedor habilitado!",
          icon: "success",
          allowEscapeKey: false,
          allowOutsideClick: false,
          timer: 2000,
          timerProgressBar: true,
          position: "top-end",
        }).then(()=>{
          this.proveedorService.modificarEstadoProveedor(this.proveedor.id).subscribe((data: Proveedor)=>{
            this.proveedor = data;
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

  buscarRubro(){
    if(this.id != 0){
      let encontrado = !this.rubrosActivos.some(objeto => objeto.id == this.proveedor.rubroId);
      return encontrado;
    }
    return false;
  }

  obtenerRubros(){
    this.proveedorService.obtenerRubros().subscribe((data: Rubro[])=>{
      this.rubros = data;
      this.rubros.sort((a, b) => {
        return a.rubro.localeCompare(b.rubro);
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

  obtenerRubrosActivos(){
    this.proveedorService.obtenerRubrosActivos().subscribe((data: Rubro[])=>{
      this.rubrosActivos = data;
      this.rubrosActivos.sort((a, b) => {
        return a.rubro.localeCompare(b.rubro);
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

  modificarRubro(rubro: Rubro){
    this.proveedorService.modificarRubro(rubro).subscribe(()=>{
      this.obtenerRubros();
      Swal.fire({
        title: 'Rubro modificado con éxito',
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
    this.obtenerRubros();
  }

  modificarEstadoRubro(rubro: Rubro){
    rubro.estado = !rubro.estado;
    this.modificarRubro(rubro);
  }

  limpiarFormulario(){
    this.proveedor = {
      codigo: '',
      razonSocial: '',
      web: '',
      email: '',
      telefono: '',
      calle: '',
      altura: '',
      codigoPostal: '',
      cuit: '',
      contactoNombre: '',
      contactoApellido: '',
      contactoTelefono: '',
      contactoEmail: '',
      contactoRol: '',
      localidad: '',
      urlImagen: '',
      provinciaId: 0,
      rubroId: 0,
      ivaId: 0
    }
  }

  formatearCuit(event: KeyboardEvent){
    if(!['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'].includes(event.key) && event.key != 'Backspace'){
      event.preventDefault();
    }
    if(event.key != 'Backspace'){
      if(this.proveedor.cuit.length == 2 || this.proveedor.cuit.length == 11){
        this.proveedor.cuit += '-';
      }
    }
  }
} 

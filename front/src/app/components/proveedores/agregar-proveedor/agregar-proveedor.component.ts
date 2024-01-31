import { Component, OnInit} from '@angular/core';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Proveedor} from '../../../models/proveedor';
import Swal from 'sweetalert2';
import { Pais } from '../../../models/pais';
import { Provincia } from '../../../models/provincia';
import { Rubro } from '../../../models/rubro';
import { Iva } from '../../../models/iva';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-agregar-proveedor',
  templateUrl: './agregar-proveedor.component.html',
  styleUrl: './agregar-proveedor.component.css'
})
export class AgregarProveedorComponent implements OnInit{


  constructor(public proveedorService: ProveedorService, private route: ActivatedRoute, private router: Router) { }
  
  formularioValido = false;
  mostrarErrores!: boolean;
  urlRegex: RegExp = /(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})/;
  emailRegex: RegExp = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
  cuitRegex: RegExp = /^(20|23|24|25|26|27|30|33|34)-?\d{8}-?\d$/;
  codigoRegex: RegExp = /^[A-Z]{2}[0-9]{3}$/;
  titulo = 'Agregar Proveedor';
  agregarOEditar = 'Agregar';
  cuitRepetido!: boolean;
  codigoRepetido!: boolean;
  razonSocialRepetida!: boolean;
  id?:number;
  detalles?:number;
  paises!: Pais[];
  provincias!: Provincia[];
  localidades: any;
  inputDesactivado = false;
  rubros: any;
  proveedores!: Proveedor[];
  mostrarAlerta: boolean = false;
  nuevoRubro: string = '';
  paisID: number = 0;
  ivas!: Iva[];

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

    this.id = parseInt(this.route.snapshot.params['id']);
    this.detalles = parseInt(this.route.snapshot.params['details']);

    this.mostrarErrores = false;
    this.proveedorService.obtenerPaises().subscribe((data: Pais[])=>{
      this.paises = data;
    });

    this.proveedorService.obtenerRubros().subscribe((data: Rubro[])=>{
      this.rubros = data;
    });

    this.proveedorService.obtenerIvas().subscribe((data: Iva[])=>{
      this.ivas = data;
    })

    if(this.id != -1){
      this.inputDesactivado = true;
      this.titulo = 'Editar Proveedor';
      this.agregarOEditar = 'Editar';
      this.proveedorService.obtenerProveedor(this.id).subscribe((data: Proveedor) => {
        this.proveedor = data;
      });
    }

    this.proveedorService.obtenerProveedores().subscribe((data: Proveedor[])=>{
      this.proveedores = data;
      if(this.id != -1){
        this.proveedores = this.proveedores?.filter((proveedor: Proveedor) => proveedor.id != this.id);
      }
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
    }).then(()=>{
      this.router.navigate(['/listar-proveedores']);
    });
  }

  agregar(formulario: any){
    if(this.formularioValido && formulario.valid){
      if(this.id == -1){
        this.proveedorService.agregarProveedor(this.proveedor).subscribe(() => {
          this.redirigir('agregado');
        }, 
        (error: HttpErrorResponse) => {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: JSON.stringify(error.error),
          });
        });
      }
      else{
        this.proveedorService.modificarProveedor(this.proveedor).subscribe( () => {
          this.redirigir('modificado');
        });
      }
    }
    else{
      this.mostrarErrores = true;
    }
  }

  onSelectPais(event: any) {
    let pais = event.target.value;
    this.proveedorService.obtenerProvincias(pais).subscribe((data: Provincia[])=>{
      this.provincias = data;
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
      case 'razonSocial':
        valido = this.proveedores.some((proveedor: Proveedor) => proveedor.razonSocial == value);
        this.formularioValido = (valido) ? false : true;
        this.razonSocialRepetida = valido;
        break;
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  agregarRubro(){    
    this.proveedorService.agregarRubro(this.nuevoRubro).subscribe(() =>{
      this.proveedorService.obtenerRubros().subscribe((data: Rubro[])=>{ 
        this.rubros = data;
        this.proveedor.rubroId = this.rubros[this.rubros.length - 1].id;
      });
      this.nuevoRubro = '';
    })
  }
} 

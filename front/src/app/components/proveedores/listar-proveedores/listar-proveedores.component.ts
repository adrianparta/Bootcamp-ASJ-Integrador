import { Component, OnInit } from '@angular/core';
import { ProveedorService } from '../../../services/service-proveedor.service';
import { Proveedor } from '../../../models/proveedor';
import 'bootstrap';
import Swal from 'sweetalert2';
import { error } from 'jquery';
import { Router } from '@angular/router';

@Component({
  selector: 'app-proveedores',
  templateUrl: './listar-proveedores.component.html',
  styleUrl: './listar-proveedores.component.css'
})
export class ListarProveedoresComponent implements OnInit{

  proveedores!: Proveedor[];
  filtro: string = '';
  emailCopiado: boolean = false;
  telefonoCopiado: boolean = false;
  webCopiada: boolean = false;
  estado: boolean = true;
  orden: string = '';

  constructor(public proveedorService: ProveedorService, private router: Router){
  }  

  obtenerProveedores(){
    this.proveedorService.obtenerProveedoresPorEstado(this.estado).subscribe((data: Proveedor[]) => {
      this.proveedores = data;
      this.ordenar();
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
  
  ngOnInit() {
    this.orden = '';
    this.estado = true;
    this.emailCopiado = false;
    this.telefonoCopiado = false;
    this.webCopiada = false;
    this.obtenerProveedores();
    this.filtro = '';
  }


  modificarEstadoProveedor(proveedor: Proveedor){
    let estado = (proveedor.estado) ? "deshabilitar " : "habilitar ";
    Swal.fire({
      title: "¿Esta seguro que desea " + estado + proveedor.razonSocial + "?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, " + estado,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Listo!",
          text: "Si hubo una confusion, siempre puedes modificar el estado del proveedor",
          icon: "success",
          position: "top-end",
          timer: 2500,
          timerProgressBar: true,          
        }).then(()=>{
          this.proveedorService.modificarEstadoProveedor(proveedor.id).subscribe(()=>{
            this.obtenerProveedores();
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

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

  copiar(string: string, tipo: string){
    switch(tipo){
      case 'email':
        this.emailCopiado = true;
        setTimeout(() => {
          this.emailCopiado = false;
        }, 2000);
        break;
      case 'telefono':
        this.telefonoCopiado = true;
        setTimeout(() => {
          this.telefonoCopiado = false;
        }, 2000);
        break;
      case 'web':
        this.webCopiada = true;
        setTimeout(() => {
          this.webCopiada = false;
        }, 2000);
        break;
    }
    navigator.clipboard.writeText(string);
  }

  activosOEliminados(estado: boolean){
    if(estado != this.estado){
      this.estado = estado;
      this.filtro = '';
      this.obtenerProveedores();
    }
  }

  ordenar(){
    this.proveedores.sort(function(a, b) {
      return a.codigo.localeCompare(b.codigo);
    });

      switch(this.orden){
        case 'razonSocial':
          this.proveedores.sort(function(a, b) {
            return a.razonSocial.localeCompare(b.razonSocial);
          });
          break;
        case 'asc':
          this.proveedores.sort(function(a, b) {
            return (a.provincia as string).localeCompare(b.provincia as string);
          });
          this.proveedores.sort(function(a, b) {
            return (a.pais as string).localeCompare(b.pais as string);
          });
          break;
        case 'desc':
          this.proveedores.sort(function(a, b) {
            return (a.provincia as string).localeCompare(b.provincia as string);
          });
          this.proveedores.sort(function(a, b) {
            return (b.pais as string).localeCompare(a.pais as string);
          });
          break;
        default:
          break;
      }
  }
}

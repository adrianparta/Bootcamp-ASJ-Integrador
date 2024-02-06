import { Component } from '@angular/core';
import { ServiceOrdenService as OrdenService } from '../../../services/service-orden.service';
import { Orden } from '../../../models/orden';
import { ProveedorService } from '../../../services/service-proveedor.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-ordenes',
  templateUrl: './listar-ordenes.component.html',
  styleUrl: './listar-ordenes.component.css'
})
export class ListarOrdenesComponent {
  offset = new Date().getTimezoneOffset() / 60;
  ordenes: Orden[] = [];
  filtro: string = '';
  estado: boolean = true;

  constructor(public ordenService: OrdenService, public proveedorService: ProveedorService){
  }  

  ngOnInit() {
    this.estado = true;
    this.obtenerOrdenes();
    this.filtro = '';
  }
  
  obtenerOrdenes(){
    this.ordenService.obtenerOrdenesPorEstado(this.estado).subscribe((data: Orden[]) => {
      this.ordenes = data;
      for (let orden of this.ordenes) {
        let fecha = new Date(orden.fechaEntrega);
        orden.fechaEntrega = new Date(fecha.setHours(fecha.getHours() + this.offset));
      }
      
    });
  }

  formatearFecha(fecha: Date): string {
    const year = fecha.getFullYear();
    const month = (fecha.getMonth() + 1).toString().padStart(2, '0');
    const day = (fecha.getDate() + 1).toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  modificarEstadoOrden(orden: Orden){
    let estado = (orden.estado) ? "cancelar" : "habilitar";
    Swal.fire({
      title: "¿Esta seguro que desea " + estado + " la orden " + orden.id + "?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, " + estado,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Listo!",
          text: "Si hubo una confusion, siempre puedes modificar el estado de la orden",
          icon: "success",
          position: "top-end",
          timer: 2500,
          timerProgressBar: true,
          allowEscapeKey: false,
          allowOutsideClick: false,
        }).then(()=>{
          this.ordenService.modificarEstadoOrden(orden.id).subscribe(()=>{
            this.obtenerOrdenes();
          });
        });
      }
    });
  }

  activosOEliminados(estado: boolean){
    this.estado = estado;
    this.filtro = '';
    this.obtenerOrdenes();
  }
}

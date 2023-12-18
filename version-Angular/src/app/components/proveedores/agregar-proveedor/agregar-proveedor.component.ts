import { Component} from '@angular/core';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';

@Component({
  selector: 'app-agregar-proveedor',
  templateUrl: './agregar-proveedor.component.html',
  styleUrl: './agregar-proveedor.component.css'
})
export class AgregarProveedorComponent{

  constructor(public serv: ServiceProveedorService) { }

  proveedor!: any;

  id: string = '';
  codigo!: string;
  razonSocial!: string;
  rubro!: string;
  web!: string;
  email!: string;
  telefono!: string;
  calle!: string;
  numero!: string;
  codigoPostal!: string;
  pais!: string;
  provincia!: string;
  localidad!: string;
  cuit!: string;
  iva!: string;
  nombre!: string;
  apellido!: string;
  telefonoPersonal!: string;
  emailPersonal!: string;
  rol!: string;

  cargarProveedor(){
    this.proveedor = {
      id: this.id,
      codigo: this.codigo,
      razonSocial: this.razonSocial,
      rubro: this.rubro,
      web: this.web,
      email: this.email,
      telefono: this.telefono,
      direccion: {
        calle: this.calle,
        numero: this.numero,
        codigoPostal: this.codigoPostal,
        pais: this.pais,
        provincia: this.provincia,
        localidad: this.localidad
      },
      personaContacto: {
        cuit: this.cuit,
        iva: this.iva,
        nombre: this.nombre,
        apellido: this.apellido,
        telefonoPersonal: this.telefonoPersonal,
        emailPersonal: this.emailPersonal,
        rol: this.rol
      }
    }
    return this.proveedor;
  }

  agregar(){
    this.serv.agregarProveedor(this.cargarProveedor());
  }
}

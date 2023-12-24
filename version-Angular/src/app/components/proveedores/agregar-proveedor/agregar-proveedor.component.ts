import { Component, OnInit} from '@angular/core';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-agregar-proveedor',
  templateUrl: './agregar-proveedor.component.html',
  styleUrl: './agregar-proveedor.component.css'
})
export class AgregarProveedorComponent implements OnInit{

  constructor(public serv: ServiceProveedorService, private route: ActivatedRoute) { }


  title = 'Agregar Proveedor';
  agregarOEditar = 'Agregar';
  id:string = this.route.snapshot.params['id'];
  
  proveedor = {
    codigo: '',
    razonSocial: '',
    rubro: '',
    web: '',
    email: '',
    telefono: '',
    direccion: {
      calle: '',
      numero: '',
      codigoPostal: '',
      pais: '',
      provincia: '',
      localidad: '',
    },
    personaContacto: {
      cuit: '',
      iva: '',
      nombre: '',
      apellido: '',
      telefonoPersonal: '',
      emailPersonal: '',
      rol: ''
    }
  }

  ngOnInit(): void {
    if(this.id != '-1'){
      this.title = 'Editar Proveedor';
      this.agregarOEditar = 'Editar';
      this.serv.getSingleProveedor(this.id).subscribe((data: any) => {
        this.proveedor = data;
      });
    }
  }

  agregar(){
      if(this.id == '-1'){
      this.serv.agregarProveedor(this.proveedor).subscribe((data: any[]) => {
        console.log(data);
      });
    }
    else{
      this.serv.updateSupplier(this.proveedor).subscribe((data: any[]) => {
        console.log(data);
      });
    }
  }

}

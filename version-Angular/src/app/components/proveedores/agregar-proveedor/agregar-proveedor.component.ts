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
    id: this.serv.getLastId() + 1,
    codigo: '',
    razonSocial: '',
    rubro: '',
    web: '',
    email: '',
    telefono: '',
    calle: '',
    numero: '',
    codigoPostal: '',
    pais: '',
    provincia: '',
    localidad: '',
    cuit: '',
    iva: '',
    nombre: '',
    apellido: '',
    telefonoPersonal: '',
    emailPersonal: '',
    rol: ''
  }

  ngOnInit(): void {
    if(this.id != '-1'){
      this.title = 'Editar Proveedor';
      this.agregarOEditar = 'Editar';
      this.proveedor.id = +this.id;
      this.proveedor = this.serv.getSingleProveedor(this.id);
    }
  }

  agregar(){
    if(this.id == '-1'){
    this.serv.agregarProveedor(this.proveedor);
    }
    else{
      this.serv.updateSupplier(this.proveedor);
    }
  }

}

import { Component, OnInit} from '@angular/core';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { ActivatedRoute } from '@angular/router';
import { Supplier } from '../../../models/supplier';

@Component({
  selector: 'app-agregar-proveedor',
  templateUrl: './agregar-proveedor.component.html',
  styleUrl: './agregar-proveedor.component.css'
})
export class AgregarProveedorComponent implements OnInit{

  constructor(public serv: ServiceProveedorService, private route: ActivatedRoute) { }
  
  title = 'Agregar Proveedor';
  agregarOEditar = 'Agregar';
  id:number = parseInt(this.route.snapshot.params['id']);
  countries: any;
  states: any;
  cities: any;
  disabledInput = false;

  IVA: string[] = [
    'Consumidor Final',
    'Excento',
    'Monotribustista',
    'No alcanzado',
    'Otro'
  ]

  proveedor: Supplier = {
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
    datosFiscales: {
      cuit: '',
      iva: '',
    },
    personaContacto: {
      nombre: '',
      apellido: '',
      telefonoPersonal: '',
      emailPersonal: '',
      rol: ''
    }
  }

  ngOnInit(): void {
    this.serv.getCountries().subscribe((data)=>{
      this.countries = data;
    });    
    this.serv.getStates().subscribe((data)=>{
      this.states = data.filter((state:any) => state.country_name == this.proveedor.direccion.pais);    
    });
    this.serv.getCities().subscribe((data)=>{
        this.cities = data.filter((city:any) => city.state_name == this.proveedor.direccion.provincia);
    });

    if(this.id != -1){
      this.disabledInput = true;
      this.title = 'Editar Proveedor';
      this.agregarOEditar = 'Editar';
      this.serv.getSingleSupplier(this.id).subscribe((data: any) => {
        this.proveedor = data;
      });
    }
    
  }

  agregar(){
      if(this.id == -1){
      this.serv.addSupplier(this.proveedor).subscribe();
    }
    else{
      this.serv.updateSupplier(this.proveedor).subscribe();
    }
  }

  onSelectCountry(country: any){
    let countryId = country.target.value
    this.serv.getStates().subscribe((data)=>{
      this.states = data.filter((state:any) => state.country_name == countryId);      
    });
  }
  
  onSelectState(state: any){
    let stateId = state.target.value;    
    this.serv.getCities().subscribe((data)=>{
      this.cities = data.filter((city:any) => city.state_name == stateId);      
    });
  }
}

import { Component, OnInit} from '@angular/core';
import { ServiceProveedorService } from '../../../services/service-proveedor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Supplier } from '../../../models/supplier';

@Component({
  selector: 'app-agregar-proveedor',
  templateUrl: './agregar-proveedor.component.html',
  styleUrl: './agregar-proveedor.component.css'
})
export class AgregarProveedorComponent implements OnInit{

  constructor(public serv: ServiceProveedorService, private route: ActivatedRoute, private router: Router) { }
  
  formularioValido = false;
  mostrarErrores!: boolean;
  urlPattern: RegExp = /(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})/;
  emailPattern: RegExp = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
  CUITPattern: RegExp = /^(20|23|24|27|30|33|34|40|50|60|70)-?\d{8}-?\d$/;
  title = 'Agregar Proveedor';
  agregarOEditar = 'Agregar';
  cuitRepetido!: boolean;
  codigoRepetido!: boolean;
  razonSocialRepetida!: boolean;
  id:number = parseInt(this.route.snapshot.params['id']);
  details:number = parseInt(this.route.snapshot.params['details']);
  countries: any;
  states: any;
  cities: any;
  disabledInput = false;
  industries: string[] = []
  supplierList!: Supplier[];

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
    },
    logo: ''
  }

  ngOnInit(): void {
    this.mostrarErrores = false;
    this.serv.getCountries().subscribe((data)=>{
      this.countries = data;
    });    
    this.serv.getStates().subscribe((data)=>{
      this.states = data.filter((state:any) => state.country_name == this.proveedor.direccion.pais);    
    });
    this.serv.getCities().subscribe((data)=>{
        this.cities = data.filter((city:any) => city.state_name == this.proveedor.direccion.provincia);
    });

    this.serv.getIndustries().subscribe((data)=>{
      this.industries = data;
    });

    if(this.id != -1){
      this.disabledInput = true;
      this.title = 'Editar Proveedor';
      this.agregarOEditar = 'Editar';
      this.serv.getSingleSupplier(this.id).subscribe((data: any) => {
        this.proveedor = data;
      });
    }

    this.serv.getSuppliers().subscribe((data)=>{
      this.supplierList = data;
      if(this.id != -1){
        this.supplierList = this.supplierList?.filter((supplier: Supplier) => supplier.id != this.id);
      }
    });
    
    if(this.details == 1){
      this.title = 'Detalles del Proveedor';
    }
  }

  agregar(formulario: any){
    if(this.formularioValido && formulario.valid){
      if(this.id == -1){
        this.serv.addSupplier(this.proveedor).subscribe();
      }
      else{
        this.serv.updateSupplier(this.proveedor).subscribe();
      }
      this.router.navigate(['/listar-proveedores']);
    }
    else{
      this.mostrarErrores = true;
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


  validarURL(){
    const valid = this.urlPattern.test(this.proveedor.web);
    valid ? this.formularioValido = true : this.formularioValido = false;
    return valid;
  }

  validarEmail(value: string){
    const valid = this.emailPattern.test(value);
    valid ? this.formularioValido = true : this.formularioValido = false;
    return valid;
  }

  validarCUIT(){
    const valid = this.CUITPattern.test(this.proveedor.datosFiscales.cuit);
    valid ? this.formularioValido = true : this.formularioValido = false;
    return valid;
  }

  comprobarRepetido(value: string, key: string): void{    
    let valid!: boolean;
    
    switch(key){
      case 'cuit':
        valid = this.supplierList.some((supplier: Supplier) => supplier.datosFiscales.cuit == value);
        valid ? this.formularioValido = false : this.formularioValido = true;
        this.cuitRepetido = valid;
        break;
      case 'codigo':
        valid = this.supplierList.some((supplier: Supplier) => supplier.codigo == value);
        valid ? this.formularioValido = false : this.formularioValido = true;
        this.codigoRepetido = valid;        
        break;
      case 'razonSocial':
        valid = this.supplierList.some((supplier: Supplier) => supplier.razonSocial == value);
        valid ? this.formularioValido = false : this.formularioValido = true;
        this.razonSocialRepetida = valid;
        break;
    }
  }

  imageNotFound(event: Event): void {
    (event.target as HTMLImageElement).src="https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
  }

} 

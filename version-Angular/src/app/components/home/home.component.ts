import { Component} from '@angular/core';
import { ServiceProveedorService } from '../../services/service-proveedor.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent{
  constructor(public serv: ServiceProveedorService){}
}

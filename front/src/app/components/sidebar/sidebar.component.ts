import { Component } from '@angular/core';
import { ProveedorService } from '../../services/service-proveedor.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  constructor(public serv: ProveedorService){}
}

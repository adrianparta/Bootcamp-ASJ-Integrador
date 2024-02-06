import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {

  constructor(private route: Router){}

  ngOnInit(): void {
  }

  comprobarURL(texto: string){
    return this.route.routerState.snapshot.url.substring(1).includes(texto);
  }

  cerrarSesion(){
    localStorage.removeItem('acceso');
    window.location.reload();
  }

  mostrarCerrarSesion(){    
    return localStorage.getItem('acceso') !== null;
  }
}

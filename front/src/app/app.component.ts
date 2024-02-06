import { Component, OnInit } from '@angular/core';
import { Usuario } from './models/usuario';
import { ServiceUsuarioService as UsuarioService } from './services/service-usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'version-Angular';
  login = false;
  mostrarErrores: boolean = false;
  usuario: Usuario = {
    usuario: '',
    contrasenia: ''
  }
  acceso: boolean = false;
  denegado: boolean = false;

  constructor(public usuarioService: UsuarioService, public router: Router){}

  ngOnInit(): void {
    this.acceso = false;
    this.usuario.usuario = '';
    this.usuario.contrasenia = '';
    this.mostrarErrores = false;
    this.denegado = false;
  }

  solicitarAcceso(formulario: any){
    if(formulario.valid){
      this.usuarioService.solicitarAcceso(this.usuario).subscribe((data: boolean) => {
          if(data){
            this.acceso = true;
            this.router.navigate(['/home']);
          }else{
            this.denegado = true;
          }
        }
      );
    }
    else{
      this.mostrarErrores = true;
    }
  }
}

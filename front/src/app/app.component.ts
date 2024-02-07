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
  mostrarErrores!: boolean;
  usuario!: Usuario;
  acceso!: boolean;
  denegado!: boolean;
  mostrarContrasenia!: boolean;

  constructor(public usuarioService: UsuarioService, public router: Router){}

  ngOnInit(): void {
    this.mostrarContrasenia = false;
    this.usuario = {
      usuario: '',
      contrasenia: ''
    };
    this.acceso = false;
    this.usuario.usuario = '';
    this.usuario.contrasenia = '';
    this.mostrarErrores = false;
    this.denegado = false;
    if(localStorage.getItem('acceso')){
      this.acceso = true;
    }
  }

  solicitarAcceso(formulario: any){
    if(formulario.valid){
      this.usuarioService.solicitarAcceso(this.usuario).subscribe((data: boolean) => {
          if(data){
            this.acceso = true;
            localStorage.setItem('acceso', 'true');
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

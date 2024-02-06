import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class ServiceUsuarioService {

  constructor(private http: HttpClient) {}

  public solicitarAcceso(usuario: Usuario): Observable<boolean>{    
    return this.http.get<boolean>('http://localhost:8080/usuarios/' + usuario.usuario + '/' + usuario.contrasenia);
  }
}

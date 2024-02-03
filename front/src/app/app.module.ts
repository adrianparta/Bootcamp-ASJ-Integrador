import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgregarProveedorComponent } from './components/proveedores/agregar-proveedor/agregar-proveedor.component';
import { ListarProveedoresComponent } from './components/proveedores/listar-proveedores/listar-proveedores.component';
import { AgregarProductoComponent } from './components/productos/agregar-producto/agregar-producto.component';
import { ListarProductosComponent } from './components/productos/listar-productos/listar-productos.component';
import { AgregarOrdenComponent } from './components/ordenes/agregar-orden/agregar-orden.component';
import { ListarOrdenesComponent } from './components/ordenes/listar-ordenes/listar-ordenes.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './components/home/home.component';
import { filtroProductosPipe } from './pipes/filtroProductos.pipe';
import { filtroCategoriasPipe } from './pipes/filtroCategorias.pipe';

@NgModule({
  declarations: [
    AppComponent,
    AgregarProveedorComponent,
    ListarProveedoresComponent,
    AgregarProductoComponent,
    ListarProductosComponent,
    AgregarOrdenComponent,
    ListarOrdenesComponent,
    SidebarComponent,
    HomeComponent,
    filtroProductosPipe,
    filtroCategoriasPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

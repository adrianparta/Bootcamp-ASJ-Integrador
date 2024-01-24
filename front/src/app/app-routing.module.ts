import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AgregarProveedorComponent } from './components/proveedores/agregar-proveedor/agregar-proveedor.component';
import { ListarProveedoresComponent } from './components/proveedores/listar-proveedores/listar-proveedores.component';
import { AgregarProductoComponent } from './components/productos/agregar-producto/agregar-producto.component';
import { ListarProductosComponent } from './components/productos/listar-productos/listar-productos.component';
import { AgregarOrdenComponent } from './components/ordenes/agregar-orden/agregar-orden.component';
import { ListarOrdenesComponent } from './components/ordenes/listar-ordenes/listar-ordenes.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'add-edit-supplier/:id/:details', component: AgregarProveedorComponent},
  {path:'listar-proveedores', component: ListarProveedoresComponent},
  {path:'add-edit-product/:id/:details', component: AgregarProductoComponent},
  {path:'listar-productos', component: ListarProductosComponent},
  {path:'agregar-orden/:id/:details', component: AgregarOrdenComponent},
  {path:'listar-ordenes', component: ListarOrdenesComponent},
  {path:'', pathMatch:'full', redirectTo:'home'}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

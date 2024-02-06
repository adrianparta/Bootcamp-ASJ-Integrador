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
  {path:'proveedores', children:[
    {path:'', component: ListarProveedoresComponent},
    {path:':id', children:[
      {path:'', component: AgregarProveedorComponent},
      {path:'detalles', component: AgregarProveedorComponent}
    ]},
  ]},
  {path:'productos', children:[
    {path:'', component: ListarProductosComponent},
    {path:':id', children:[
      {path:'', component: AgregarProductoComponent},
      {path:'detalles', component: AgregarProductoComponent}
    ]},
  ]},
  {path:'ordenes', children:[
    {path:'', component: ListarOrdenesComponent},
    {path:':id', children:[
      {path:'', component: AgregarOrdenComponent},
      {path:'detalles', component: AgregarOrdenComponent}
    ]}
  ]},
  {path:'', pathMatch:'full', redirectTo:'home'}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

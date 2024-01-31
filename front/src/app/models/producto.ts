import { Categoria } from "./categoria"
import { Proveedor } from "./proveedor"

export interface Producto{
    id?: number;
    codigo: string;
    nombre: string;
    descripcion: string;
    precio: number;
    imagen_url: string;
    estado?: boolean;
    categoriaId: number;
    categoria?: string;
    proveedorId: number;
    proveedor?: string;
}
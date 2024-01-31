import { Detalle } from "./detalle";

export interface Orden{
    id?: number,
    fechaEmision?: Date,
    fechaEntrega: Date,
    info: string,
    estado?: boolean,
    total?: number,
    proveedorId: number,
    proveedor?: String,
    detalles: Detalle[]
}
import { Proveedor } from "./proveedor";

export interface Orden{
    id?: number,
    fechaEmision?: Date,
    fechaEntrega: Date,
    info: string,
    estado: boolean,
    total: number,
    proveedor: Proveedor
}
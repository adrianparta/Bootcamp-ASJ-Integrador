import { Proveedor } from "./proveedor";

export interface Orden{
    id?: number,
    fechaEmision: Date,
    fechaEntrega: Date,
    info: string,
    estado: boolean,
    created_at: Date,
    updated_at: Date,
    total: number,
    proveedor: Proveedor
}
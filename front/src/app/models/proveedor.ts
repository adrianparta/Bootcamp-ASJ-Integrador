import { Iva } from "./iva"
import { Provincia } from "./provincia"
import { Rubro } from "./rubro"

export interface Proveedor{
    id?: number,
    codigo: string,
    razonSocial: string,
    web: string,
    email: string,
    telefono: string,
    calle: string,
    altura: string,
    codigoPostal: string,
    cuit: string,
    contactoNombre: string,
    contactoApellido: string,
    contactoTelefono: string,
    contactoEmail: string,
    contactoRol: string,
    estado: boolean,
    localidad: string,
    url_imagen: string,
    created_at: Date,
    updated_at: Date
    provincia: Provincia
    rubro: Rubro,
    iva: Iva
}
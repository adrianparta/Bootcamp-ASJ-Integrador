export interface Proveedor{
    id?: number;
    codigo: string;
    razonSocial: string;
    web: string;
    email: string;
    telefono: string;
    calle: string;
    altura: string;
    codigoPostal: string;
    cuit: string;
    contactoNombre: string;
    contactoApellido: string;
    contactoTelefono: string;
    contactoEmail: string;
    contactoRol: string;
    estado?: boolean;
    localidad: string;
    urlImagen: string;
    provinciaId: number;
    provincia?: String;
    pais?: String;
    rubroId: number;
    ivaId: number;
}
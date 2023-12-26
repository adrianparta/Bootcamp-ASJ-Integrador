export interface Supplier{
    id?: number,
    codigo: string,
    razonSocial: string,
    rubro: string,
    web: string,
    email: string,
    telefono: string,
    direccion: {
        calle: string,
        numero: string,
        codigoPostal: string,
        pais: string,
        provincia: string,
        localidad: string,
    },
    datosFiscales: {
        cuit: string,
        iva: string,
    },
    personaContacto: {
        nombre: string,
        apellido: string,
        telefonoPersonal: string,
        emailPersonal: string,
        rol: string,
    }   
}
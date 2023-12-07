const codigo = document.getElementById("inputCodigo");
const razonSocial = document.getElementById("inputRazonSocial");
const rubro = document.getElementById("inputRubro");
const sitio = document.getElementById("inputSitioWeb");
const email = document.getElementById("inputEmail");
const telefono = document.getElementById("inputTelefono");
const calle = document.getElementById("inputCalle");
const numeroCalle = document.getElementById("inputNumeroCalle");
const codigoPostal = document.getElementById("inputCodigoPostal");
const pais = document.getElementById("inputPais");
const provincia = document.getElementById("inputProvincia");
const localidad = document.getElementById("inputLocalidad");
const CUIT = document.getElementById("inputCUIT");
const IVA = document.getElementById("inputIVA");
const nombre = document.getElementById("inputNombre");
const apellido = document.getElementById("inputApellido");
const telefonoContacto = document.getElementById("inputTelefonoContacto");
const emailContacto = document.getElementById("inputEmailContacto");
const rol = document.getElementById("inputRol");

const button = document.getElementById("agregar");

let aux = [];

button.addEventListener("click",()=>{
    aux = {
        codigo: codigo.value,
        razonSocial: razonSocial.value,
        rubro: rubro.value,
        sitio: sitio.value,
        email: email.value,
        telefono: telefono.value,
        calle: calle.value,
        numeroCalle: numeroCalle.value,
        codigoPostal: codigoPostal.value,
        pais: pais.value,
        provincia: provincia.value,
        localidad: localidad.value,
        CUIT: CUIT.value,
        IVA: IVA.value,
        nombre: nombre.value,
        apellido: apellido.value,
        telefonoContacto: telefonoContacto.value,
        emailContacto: emailContacto.value,
        rol: rol.value
    }

    console.log(aux);
    console.log("1");
    if(localStorage.getItem("proveedores") === null){
        localStorage.setItem("proveedores", JSON.stringify(aux));
    console.log("2");

    }
    else{
        aux = JSON.parse(localStorage.getItem("proveedores"));
        aux = (
            {
            codigo: codigo.value,
            razonSocial: razonSocial.value,
            rubro: rubro.value,
            sitio: sitio.value,
            email: email.value,
            telefono: telefono.value,
            calle: calle.value,
            numeroCalle: numeroCalle.value,
            codigoPostal: codigoPostal.value,
            pais: pais.value,
            provincia: provincia.value,
            localidad: localidad.value,
            CUIT: CUIT.value,
            IVA: IVA.value,
            nombre: nombre.value,
            apellido: apellido.value,
            telefonoContacto: telefonoContacto.value,
            emailContacto: emailContacto.value,
            rol: rol.value
            }
        );
        localStorage.setItem("proveedores", JSON.stringify(aux));

}
});
function cargarLocalStorage(){
    aux = {
        codigo: codigo.value,
        razonSocial: razonSocial.value,
        rubro: rubro.value,
        sitio: sitio.value,
        email: email.value,
        telefono: telefono.value,
        calle: calle.value,
        numeroCalle: numeroCalle.value,
        codigoPostal: codigoPostal.value,
        pais: pais.value,
        provincia: provincia.value,
        localidad: localidad.value,
        CUIT: CUIT.value,
        IVA: IVA.value,
        nombre: nombre.value,
        apellido: apellido.value,
        telefonoContacto: telefonoContacto.value,
        emailContacto: emailContacto.value,
        rol: rol.value
    }

    console.log(aux);
    console.log("1");
    if(localStorage.getItem("proveedores") === null){
        localStorage.setItem("proveedores", JSON.stringify(aux));
    console.log("2");

    }
    else{
        aux = JSON.parse(localStorage.getItem("proveedores"));
        aux.push(
            {
            codigo: codigo.value,
            razonSocial: razonSocial.value,
            rubro: rubro.value,
            sitio: sitio.value,
            email: email.value,
            telefono: telefono.value,
            calle: calle.value,
            numeroCalle: numeroCalle.value,
            codigoPostal: codigoPostal.value,
            pais: pais.value,
            provincia: provincia.value,
            localidad: localidad.value,
            CUIT: CUIT.value,
            IVA: IVA.value,
            nombre: nombre.value,
            apellido: apellido.value,
            telefonoContacto: telefonoContacto.value,
            emailContacto: emailContacto.value,
            rol: rol.value
            }
        );
        localStorage.setItem("proveedores", JSON.stringify(aux));
    console.log("3");

    }
}
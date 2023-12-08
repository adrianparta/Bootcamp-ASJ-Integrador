const numeroOrden = document.getElementById("inputNumeroOrden");
const fechaEmision = document.getElementById("inputFechaEmision");
const fechaEntrega = document.getElementById("inputFechaEntrega");
const calle = document.getElementById("inputCalle");
const numeroCalle = document.getElementById("inputNumeroCalle");
const codigoPostal = document.getElementById("inputCodigoPostal");
const pais = document.getElementById("inputPais");
const provincia = document.getElementById("inputProvincia");
const localidad = document.getElementById("inputLocalidad");
const proveedor = document.getElementById("selectProveedor");
const productos = document.getElementById("selectProductos");
const cantidad = document.getElementById("inputCantidad");
const total = document.getElementById("inputTotal");

const button = document.getElementById("agregar");

let aux = {};
function llenarAux(){
    aux = {
        numeroOrden: numeroOrden.value,
        fechaEmision: fechaEmision.value,
        fechaEntrega: fechaEntrega.value,
        calle: calle.value,
        numeroCalle: numeroCalle.value,
        codigoPostal: codigoPostal.value,
        pais: pais.value,
        provincia: provincia.value,
        localidad: localidad.value,
        proveedor: proveedor.value,
        productos: productos.value,
        cantidad: cantidad.value,
        total: total.value,
    }
}

button.addEventListener("click",()=>{
    llenarAux();
    if(localStorage.getItem("ordenes") === null){
        localStorage.setItem("ordenes", JSON.stringify(Array(aux)));
    }
    else{
        let aux2 = JSON.parse(localStorage.getItem("ordenes"));
        console.log(aux2);

        aux2.push(aux);
        localStorage.setItem("ordenes", JSON.stringify(aux2));

}
});
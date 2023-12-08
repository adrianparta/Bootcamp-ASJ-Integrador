const proveedor = document.getElementById("selectProveedor");
const codigo = document.getElementById("inputCodigo");
const categoria = document.getElementById("selectCategoria");
const nombreProducto = document.getElementById("inputNombreProducto");
const descripcion = document.getElementById("inputDescripcion");
const precio = document.getElementById("inputPrecio");

const button = document.getElementById("agregar");

let aux = {};
function llenarAux(){
    aux = {
        proveedor: proveedor.value,
        codigo: codigo.value,
        categoria: categoria.value,
        nombreProducto: nombreProducto.value,
        descripcion: descripcion.value,
        precio: precio.value,
    }
}

button.addEventListener("click",()=>{
    llenarAux();
    if(localStorage.getItem("productos") === null){
        localStorage.setItem("productos", JSON.stringify(Array(aux)));
    }
    else{
        let aux2 = JSON.parse(localStorage.getItem("productos"));
        console.log(aux2);

        aux2.push(aux);
        localStorage.setItem("productos", JSON.stringify(aux2));

}
});